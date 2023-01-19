#!/bin/bash
set -e

./build.sh

# Update the set of services and
# build and execute the system tests
pushd client
./deploy.sh
sleep 5
./test.sh
popd

# Cleanup the build images
docker image prune -f