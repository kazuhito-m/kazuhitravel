#!/bin/bash

SERVER=http://localhost:8080

#PARAM_PART='?moreThanNotContainId=1679521360'
PARAM_PART=''

curl -X GET "${SERVER}/api/itinerary${PARAM_PART}"
