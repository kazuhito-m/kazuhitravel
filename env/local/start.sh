#!/bin/bash

# 「このスクリプトがある場所」まで移動
SCRIPT_DIR=$(cd $(dirname $(readlink -f $0 || echo $0));pwd -P)
cd ${SCRIPT_DIR}

if docker ps | grep 'kazuhitravel-db-for-test' > /dev/null; then
  echo "イメージ・コンテナともに既にあるなら停止・削除"
  docker-compose down

  image_name=$(grep 'image:' ./docker-compose.yaml | sed -e 's/.*image: "//g' | sed -e 's/\"//g')
  docker rmi -f ${image_name}
fi

# 実行
docker-compose up -d

# この後は、redis-cli 入ってるなら、`redis-cli --raw` で中身を覗いてください。
