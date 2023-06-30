#!/bin/bash
source /home/ec2-user/partners-app-partners-admin/.env

sudo cp /home/ec2-user/partners-app-partners-admin/infra/configs/nginx/nginx.conf /etc/nginx/nginx.conf
if [ "$PROFILE" == "prod" ]; then
  SERVER_NAME="partners-admin.deermobility.com"
else
  SERVER_NAME="partners-admin.stage.deermobility.com"
fi
sudo sed -i "s/\$SERVER_NAME/$SERVER_NAME/g" /etc/nginx/nginx.conf
sudo systemctl restart nginx
