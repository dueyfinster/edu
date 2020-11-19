#!/bin/bash
cp ../requirements.txt requirements.txt
docker build -t dueyfinster/blackteam .
