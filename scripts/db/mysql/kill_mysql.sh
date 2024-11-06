#!/bin/bash

MYSQL_STOP_CMD="sudo docker stop mysql"
MYSQL_RM_CMD="sudo docker rm mysql"

echo $MYSQL_STOP_CMD
result=$(eval $MYSQL_STOP_CMD)
if [ $? -ne 0 ]; then
	echo $result
fi

echo $MYSQL_RM_CMD
result=$(eval $MYSQL_RM_CMD)
if [ $? -ne 0 ]; then
	echo $result
	exit 1
fi
