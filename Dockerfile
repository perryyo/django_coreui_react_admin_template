# 1. ubuntu image
FROM ubuntu:16.04
MAINTAINER kani@lunit.io

# 2. python install
RUN apt-get update
RUN apt-get install -y software-properties-common
RUN add-apt-repository -y ppa:fkrull/deadsnakes
RUN apt-get update
RUN apt-get install -y --no-install-recommends python3.6 python3-pip python3-setuptools python3-wheel curl
RUN python3 -m pip install pip --upgrade

# 3. install npm
RUN curl -sL https://deb.nodesource.com/setup_10.x | bash
RUN apt-get install -y nodejs

# 3. copy source
COPY . /usr/src/webapp

# 5. npm install
WORKDIR /usr/src/webapp/frontend
RUN     npm install
RUN     npm run build

# 6. PIP package install
WORKDIR /usr/src/webapp
RUN     pip3 install -r requirements.txt

# 7. run server
EXPOSE 8000
WORKDIR /usr/src/webapp
ENTRYPOINT ["python3","manage.py","runserver","0:8000", "--setting=backend.settings.development"]