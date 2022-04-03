#!/bin/bash
set -e

psql -U postgres <<-EOSQL
  CREATE DATABASE app_user_db;
  GRANT ALL PRIVILEGES ON DATABASE app_user_db TO postgres;
EOSQL