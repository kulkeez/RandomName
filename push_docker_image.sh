#!/bin/bash
#docker tag simulap-api:0.1 hub.docker.hpecorp.net/simulap_devel/simulap-api:0.1
echo "Publishing the Docker image to the public Docker Hub Registry ..."
docker push kulkeez/random-name:0.1
echo "Published the Docker image within the Public Docker Hub Registry!"

