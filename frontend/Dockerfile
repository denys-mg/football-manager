FROM node:18.16.0-alpine
WORKDIR /usr/src/app
COPY package.json .
RUN npm i -g @angular/cli
RUN npm i
COPY . .
RUN ng build
CMD ng serve --host 0.0.0.0
