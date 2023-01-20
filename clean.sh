#!/bin/bash
set -e

# Build and install the libraries
# abstracting away from using the
# RabbitMq message queue
pushd messaging-utilities-3.3
mvn clean
popd

# Build the services
pushd code-with-quarkus
mvn clean
popd

pushd TokenService
mvn clean
popd

pushd CustomerService
mvn clean
popd

pushd MerchantService
mvn clean
popd