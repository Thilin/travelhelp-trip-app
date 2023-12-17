#!/bin/bash

# -- > Create DynamoDb Table
echo "########### Creating dynamodb table###########"
awslocal dynamodb create-table --cli-input-json file:///localstack/tables/travelhelp-trip.json
# --> List DynamoDb Tables
echo Listing tables ...
echo $(awslocal dynamodb list-tables)