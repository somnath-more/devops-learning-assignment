# Devops-Learning-Assignment
## Steps To create a docker image 

1. Create a Docker File  
```Dockerfile
FROM node:18-slim
# This will create myapp directory
WORKDIR /myapp
# This will copy content to myapp
COPY . /myapp
#install dependacies in container
RUN npm install
# After installing this Application , you can start container the this command will execute it
CMD [ "npm","start" ]
 
```
2 . create an image 
```bash
docker build . -t frontend-demo
```
3. if you want to push image on docker hub
```bash
# Tag the local image with a different tag
docker tag frontend-demo:latest somnathmore/my-demo-docker-app:version1

# Push the tagged image to the Docker registry
docker push somnathmore/my-demo-docker-app:version1
```
3. run a container
docker run -d -p 3000:3000 --name=frontend-container-demo1 frontend-demo:latest