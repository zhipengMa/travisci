#!/bin/bash
sudo echo "$DOCKER_PASSWORD" | docker login --username="$DOCKER_USERNAME" registry.cn-beijing.aliyuncs.com --password-stdin
sudo docker build -t travis .
sudo docker tag $(docker images -q --filter reference=travis*:*) registry.cn-beijing.aliyuncs.com/zhipengma/travis-ci:travis
sudo docker push registry.cn-beijing.aliyuncs.com/zhipengma/travis-ci:travis
