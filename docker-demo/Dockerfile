
# base docker image for background
FROM openjdk:17.0.2-jdk

ADD target/docker-demo-0.0.1-SNAPSHOT.jar docker-demo-app.jar

ENTRYPOINT ["java","-jar","docker-demo-app.jar"]

