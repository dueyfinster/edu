FROM ipython/scipyserver:latest
ADD . /reqs
WORKDIR /reqs
RUN pip2 install -r requirements.txt
RUN pip3 install -r requirements.txt
