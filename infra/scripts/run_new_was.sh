#!/bin/bash
source /home/ec2-user/.env

nohup java -jar \
-Dspring.profiles.active="$PROFILE" \
/home/ec2-user/build/libs/blackcompany-*-SNAPSHOT.jar > /dev/null 2>&1 &

echo "> application started"
exit 0
