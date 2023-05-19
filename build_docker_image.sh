#!/bin/bash

SECONDS=0

export BUILD_DATE=$(date +%d-%b-%Y)

# Compile the code and build the jar file 
mvn clean compile package -DskipTests

echo "Build of the RandomName Generator Docker Image started at: $(date)"
echo "BUILD_DATE = ${BUILD_DATE}" 

sudo docker build . --network host --rm --tag kulkeez/random-name:0.1 

echo "Build of the RandomName Generator Docker Image ended at: $(date)"
ELAPSED=$SECONDS
echo "RandomName Generator Docker Build : $(($ELAPSED / 60)) minutes and $(($ELAPSED % 60)) seconds elapsed."
