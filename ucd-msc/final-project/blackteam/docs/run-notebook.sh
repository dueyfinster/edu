#!/bin/bash
docker stop notebook
docker rm notebook
docker build -t notebookbase .
docker run --name notebook -d -p 8888:8888 -e "PASSWORD=" -e "USE_HTTP=1" notebookbase
