#!/bin/bash
set -e
sudo -type f -exec chmod 755 {} \;

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