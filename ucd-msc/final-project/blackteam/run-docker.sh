#!/bin/bash

if [[ "$OSTYPE" == "darwin"* ]]; then
    eval "$(docker-machine env dev)"
fi

docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
docker-compose build
docker-compose up -d
docker-compose run web /usr/bin/python3 manage.py migrate
docker-compose run web /usr/bin/python3 manage.py createadmin 
docker-compose run web /usr/bin/python3 manage.py importcsv
