#!/usr/bin/env bash

stopExistingInstances() {
  docker-compose down -v
}

forceCleanUpInstances() {
  docker rm -f $(docker ps -a -q)
}

cd elasticsearch-docker
stopExistingInstances
