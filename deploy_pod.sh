#!/bin/bash

echo "Deploying/launching the pod into the Kubernetes cluster ..."
kubectl create -f deploy-pod.yaml 
kubectl wait --for=condition=Available pod/random-name-pod
kubectl get pods
kubectl port-forward pod/random-name-pod 8080
