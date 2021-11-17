#!/usr/bin/env bash

createSslMaterial() {
  echo 'Creating ssl material...'
  docker-compose -f create-certs.yml run --rm create_certs
}

startElasticSearchInstances() {
  echo 'Booting up ElasticSearch...'
  docker-compose up -d
}

exportCaCertificateOfElasticSearch() {
  echo 'Exporting ElasticSearch CA certificate to current directory...'
  docker run -v es_certs:/certs --network=es_default docker.elastic.co/elasticsearch/elasticsearch:7.15.1 cat /certs/ca/ca.crt > ca.crt
}

cd elasticsearch-docker/
createSslMaterial
startElasticSearchInstances
exportCaCertificateOfElasticSearch