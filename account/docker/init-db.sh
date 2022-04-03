#!/bin/bash
set -e

psql -U postgres <<-EOSQL
  CREATE DATABASE account_db;
  GRANT ALL PRIVILEGES ON DATABASE account_db TO postgres;
EOSQL