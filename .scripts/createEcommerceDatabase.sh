#!/bin/bash
CONTAINER_NAME=e-commerce-db-1
docker exec -it $CONTAINER_NAME psql -U postgres -d postgres \
    -c "CREATE database ecommerce" \
    -c "CREATE USER postgres WITH ENCRYPTED PASSWORD 'postg\';" \
    -c "GRANT ALL PRIVILEGES ON DATABASE ecommerce TO postgres;" \
    -c "\l" \
    /