FROM node:18-slim
# This will create myapp directory
WORKDIR /myapp
# This will copy content to myapp
COPY . /myapp
#install dependacies in container
RUN npm install
# After installing this Application , you can start container the this command will execute it
CMD [ "npm","start" ]
