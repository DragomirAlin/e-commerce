#!/bin/bash
CONTAINER_NAME=e-commerce-db-1
docker exec -it $CONTAINER_NAME psql -U postgres -d postgres \
    -c "CREATE database keycloak" \
    -c "CREATE USER kcuser WITH ENCRYPTED PASSWORD 'kcuser1234';" \
    -c "GRANT ALL PRIVILEGES ON DATABASE keycloak TO kcuser;" \
    -c "\l" \
    /