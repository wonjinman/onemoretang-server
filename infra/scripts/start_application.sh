#!/bin/bash
source /home/ec2-user/.env

PORT_A=8080
PORT_B=8081

if [ -n "$(lsof -i :$PORT_A)" ]; then
  OLD_PORT=$PORT_A
  NEW_PORT=$PORT_B
elif [ -n "$(lsof -i :$PORT_B)" ]; then
  OLD_PORT=$PORT_B
  NEW_PORT=$PORT_A
else
  NEW_PORT=$PORT_A
fi

echo "> start new application"

nohup java -jar \
-Xmx512m \
-Dspring.profiles.active="$PROFILE" \
-Dserver.port="$NEW_PORT" \
/home/ec2-user/build/libs/blackcompany-*-SNAPSHOT.jar > /dev/null 2>&1 &

echo "> waiting for new application to be ready"
sleep 10

for retry_count in {1..12}
do
  UP=$(curl -s http://localhost:$NEW_PORT/actuator/health | grep 'UP')
  if [[ $UP ]]; then
        echo "> application up!"
        break
  fi

  if [ "$retry_count" -eq 12 ]
  then
    echo "> application is not working."
    exit 1
  fi

  echo "> wait..."
  sleep 10
done

# switch nginx ports
sudo sed -i "s/http:\/\/localhost:\($PORT_A\|$PORT_B\);/http:\/\/localhost:$NEW_PORT;/g" /etc/nginx/nginx.conf
sudo systemctl reload nginx
echo "> nginx reloaded"

# terminate old application
if [[ $OLD_PORT ]]; then
        echo "> terminate old application with port $OLD_PORT"
        OLD_PID=$(lsof -i :$OLD_PORT | awk 'NR==2{print $2}')
        if [[ $OLD_PID ]]; then
                echo "> found $OLD_PID. kill process"
                sudo kill "$OLD_PID"
        fi
fi
