#!/bin/bash
set -e

psql -U postgres <<-EOSQL
  CREATE DATABASE account_service_db;
  GRANT ALL PRIVILEGES ON DATABASE account_service_db TO postgres;
EOSQL