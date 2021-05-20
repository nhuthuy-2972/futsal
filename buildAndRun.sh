#!/bin/sh
mvn clean package && docker build -t com.axonactive.trainning/futsalmangement .
docker rm -f futsalmangement || true && docker run -d -p 8080:8080 -p 4848:4848 --name futsalmangement com.axonactive.trainning/futsalmangement 
