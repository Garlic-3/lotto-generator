#!/bin/bash

MYSQL_CONTAINER_NAME="mysql"

MYSQL_CREATE_USER_SQL="create table user (
	id bigint not null auto_increment,
	username varchar(30) not null unique,
	password varchar(100) not null,
	role tinyint not null,
	create_date date not null,
	update_date date not null,
	delete_date date,
	point bigint not null,
	nickname varchar(20) not null unique,
	email varchar(40) not null unique,
	phone varchar(11) unique,
	primary key (id)
);"

CREATE_USER_CMD="sudo docker exec -i $MYSQL_CONTAINER_NAME mysql -u$DB_USER -p$DB_PASSWORD -D$DB_NAME -e \"$MYSQL_CREATE_USER_SQL\""

echo $CREATE_USER_CMD
result=$(eval $CREATE_USER_CMD)
if [ $? -ne 0 ]; then
	echo $result
	exit 1
fi
