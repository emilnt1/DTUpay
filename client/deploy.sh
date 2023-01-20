#!/bin/bash
set -e
docker image prune -f
docker-compose up -d rabbitMq
sleep 10
docker-compose up -d dtupay_backend
sleep 15
docker-compose up -d tokenservice customerservice merchantservice

