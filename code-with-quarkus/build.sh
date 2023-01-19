#!/bin/bash
set -e
mvn clean package
docker-compose build code-with-quarkus