#!/bin/bash
set -e

psql -U postgres <<-EOSQL
  CREATE DATABASE user_service_db;
  GRANT ALL PRIVILEGES ON DATABASE user_service_db TO postgres;
EOSQL