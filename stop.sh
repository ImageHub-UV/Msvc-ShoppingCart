#!/bin/bash


endopints_file="endpoints.yaml"
config_file="configmap.yaml"
database_file="postgresql.yaml"
application_file="shopping_cart.yaml"


if [ ! -f $endopints_file ]; then
    echo -e "Error: file ($endopints_file) not found"
    exit 1
fi


if [ ! -f $config_file ]; then
    echo -e "Error: file ($endopints_file) not found"
    exit 1
fi

if [ ! -f $database_file ]; then
    echo -e "Error: file ($database_file) not found"
    exit 1
fi

if [ ! -f $application_file ]; then
    echo -e "Error: file ($application_file) not found"
    exit 1
fi

kubectl delete -f $endopints_file
kubectl delete -f $config_file
kubectl delete -f $database_file
kubectl delete -f $application_file
