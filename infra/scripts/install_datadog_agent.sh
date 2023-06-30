#!/bin/bash
source /home/ec2-user/partners-app-partners-admin/.env
sudo yum install -y jq

DD_AGENT_MAJOR_VERSION=7 DD_API_KEY=$(aws --region ap-northeast-2 ssm get-parameter --name /partners/"$PROFILE"/datadog-api-key --with-decryption | jq '.Parameter.Value' -r) DD_SITE="datadoghq.com" bash -c "$(curl -L https://s3.amazonaws.com/dd-agent/scripts/install_script.sh)"

sudo sed -i 's/# logs_enabled: false/logs_enabled: true/g' /etc/datadog-agent/datadog.yaml

sudo cp /home/ec2-user/partners-app-partners-admin/infra/configs/datadog/java.yml /etc/datadog-agent/conf.d/java.yml
sudo sed -i "s/\$PROFILE/$PROFILE/g" /etc/datadog-agent/conf.d/java.yml
sudo cat /home/ec2-user/partners-app-partners-admin/infra/configs/datadog/datadog.yaml >> /etc/datadog-agent/datadog.yaml

sudo systemctl restart datadog-agent
