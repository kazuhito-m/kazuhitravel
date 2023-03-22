#!/bin/bash

SERVER=http://localhost:8080

SCRIPT_DIR=$(
  cd $(dirname $(readlink -f $0 || echo $0))
  pwd -P
)
cd ${SCRIPT_DIR}

curl -X POST "${SERVER}/api/trucking" \
  -H "Content-type: application/json" \
  -d '{"longitude":100, "latitude":200}'
