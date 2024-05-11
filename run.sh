#!/bin/bash


endopints_file="endpoints.yaml"
database_file="postgresql.yaml"
application_file="shopping_cart.yaml"


if [ ! -f $endopints_file]; then
	echo -e "Error: endpoint configuration file ($endopints_file) not found"
	exit 1
fi

if [ ! -f $database_file ]; then
	echo -e "Error: database configuration file ($database_file) not found"
	exit 1
fi

if [ ! -f $application_file ]; then
	echo -e "Error: application configuration file ($application_file) not found"
	exit 1
fi

kubectl apply -f $endopints_file
kubectl apply -f $database_file
kubectl apply -f $application_file
