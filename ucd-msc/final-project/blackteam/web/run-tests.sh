#!/bin/bash
Xvfb :10 -ac > /dev/null &
python3 manage.py test
