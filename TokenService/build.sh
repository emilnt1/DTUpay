#!/bin/bash
set -e
mvn clean install
mvn package
docker-compose build