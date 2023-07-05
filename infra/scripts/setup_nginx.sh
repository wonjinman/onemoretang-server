#!/bin/bash
source /home/ec2-user/.env

sudo cp -f /home/ec2-user/infra/configs/nginx/logrotate.conf /etc/logrotate.d/nginx
sudo cp -f /home/ec2-user/infra/configs/nginx/nginx.conf /etc/nginx/nginx.conf

if [ "$PROFILE" == "prod" ]; then
  SERVER_NAME="onemoretang.com"
fi
sudo sed -i "s/\$SERVER_NAME/$SERVER_NAME/g" /etc/nginx/nginx.conf

sudo systemctl restart nginx
