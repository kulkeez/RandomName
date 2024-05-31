#!/bin/bash
#docker tag random-name:0.1 kulkeez/random-name:0.1
echo "Publishing the Docker image to the public Docker Hub Registry ..."
docker push kulkeez/random-name:0.1
echo "Published the Docker image within the Public Docker Hub Registry!"

