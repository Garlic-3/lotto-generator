#!/bin/bash

DATABASE_DIR="$HOME/database/"
PULL_MYSQL_CMD="sudo docker pull mysql:latest"
MKDIR_DATA_DIRECTORY="mkdir $DATABASE_DIR"
MYSQL_RUN_CMD="sudo docker run -d \
	--name=mysql \
	-eMYSQL_ROOT_PASSWORD=$DB_ROOT_PASSWORD \
	-eMYSQL_DATABASE=$DB_NAME \
	-eMYSQL_USER=$DB_USER \
	-eMYSQL_PASSWORD=$DB_PASSWORD \
	-v$DATABASE_DIR:/var/lib/mysql \
	-p$DB_PORT:3306 mysql \
	"

echo $PULL_MYSQL_CMD
result=$(eval $PULL_MYSQL_CMD)
if [ $? -ne 0 ]; then
	echo $result
fi

echo $MKDIR_DATA_DIRECTORY
result=$(eval $MKDIR_DATA_DIRECTORY)
if [ $? -ne 0 ]; then
	echo $result
fi

echo $MYSQL_RUN_CMD
result=$(eval $MYSQL_RUN_CMD)
if [ $? -ne 0 ]; then
	echo $result
	exit 1
fi
