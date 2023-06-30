#!/bin/bash

# Crawl current connected port of WAS
CURRENT_PORT=$(cat /home/ec2-user/service_url.inc  | grep -Po '[0-9]+' | tail -1)
TARGET_PORT=0

echo "> Nginx currently proxies to ${CURRENT_PORT}."

# Toggle port number
if [ ${CURRENT_PORT} -eq 8080 ]; then
    TARGET_PORT=8081
elif [ ${CURRENT_PORT} -eq 8081 ]; then
    TARGET_PORT=8080
else
    echo "> No WAS is connected to nginx"
    exit 1
fi

# kill old WAS
sudo kill $(lsof -Fp -i TCP:${TARGET_PORT} | grep -Po 'p[0-9]+' | grep -Po '[0-9]+')

echo "> Killed old WAS to ${TARGET_PORT}."
