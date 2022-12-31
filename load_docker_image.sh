#!/bin/bash

# check if kind cluster is up and running 
kind get clusters
kubectl get nodes -o wide
docker images | grep random-name

# command to load Docker image into our kind cluster (dev-kind) node
kind load docker-image random-name:0.1 --name dev-kind