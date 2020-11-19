#!/bin/bash

echo -e "Host ucd\n\tStrictHostKeyChecking no\n\tHostname csi420-01-vm2.ucd.ie\n\tUser nlstudent" >> ~/.ssh/config
ssh ucd "cd blackteam && git fetch && git reset --hard origin/master && ./run-docker.sh"
