#!/bin/bash


endopints_file="endpoints.yaml"
database_file="postgresql.yaml"
application_file="shopping_cart.yaml"


if [ ! -f $database_file ]; then
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

kubectl delete -f $endopints_file
kubectl delete -f $database_file
kubectl delete -f $application_file
