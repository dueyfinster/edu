FROM ubuntu:14.04
ENV LANG C.UTF-8
ENV DISP 10
ENV DISPLAY :${DISP}
RUN mkdir -p /usr/src/app/static
COPY . /usr/src/app/static
WORKDIR /usr/src/app/static
RUN apt-get -y update 
RUN apt-get install -y python3 python3-pip python3-dev xvfb firefox libpq-dev build-essential
RUN pip3 install -r requirements.txt
