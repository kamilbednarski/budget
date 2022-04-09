#!/bin/bash
set -e

psql -U postgres <<-EOSQL
  CREATE DATABASE registration_service_db;
  GRANT ALL PRIVILEGES ON DATABASE registration_service_db TO postgres;
EOSQL