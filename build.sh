#!/bin/bash
set -e

# Build and install the libraries
# abstracting away from using the
# RabbitMq message queue
pushd messaging-utilities-3.3
./build.sh
popd

pushd TokenService
./build.sh
popd

pushd MerchantService
./build.sh
popd

pushd CustomerService
./build.sh
popd


# Build the services
pushd code-with-quarkus
./build.sh
popd

