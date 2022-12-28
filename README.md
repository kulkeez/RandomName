# Docker 101 :tada: :sparkles: :v: 

You cloned a project that demonstrates use of Docker containers to deploy a Java application using Spring Boot.
The Docker image is built using a Dockerfile`and is based on the small [Alpine Linux](https://alpinelinux.org/ "Alpine Linux") base image (~80 MB).

## Prerequisites

A Spring application that generates random names needs to be built and baked within a bare-bones Alpine container. 
From command-line, use the command: ```mvn package spring-boot:repackage ```
	
### Unit test the 'Random Name Generator'
This Spring Boot application can also be launched from command-line using the command: ```mvn spring-boot:run ```
This will result in the embedded Tomcat server being launched and listening on port 8080. 
Next, open your browser and browse to `http://localhost:8080/`
You should see the message: `Tue Mar 17 11:56:37 GMT 2020: Hello, ELECTRONIC_BIND! Greetings from RandomName Generator!`

We use Spring's support for embedding the Tomcat servlet container as the HTTP runtime, instead of deploying to an external instance.

Various Actuator RESTful end points which are added to this application are: 
 * http://localhost:8080/actuator/health
 * http://localhost:8080/actuator/info 
 * http://localhost:8080/actuator/mappings 
 * http://localhost:8080/actuator/env
 * http://localhost:8080/actuator/metrics 

For more on Actuator end points documentation, check the [Spring Boot Actuator Web API Documentation](https://docs.spring.io/spring-boot/docs/2.2.2.RELEASE/actuator-api/html/) 
and the [Spring Boot Reference Documentation](https://docs.spring.io/spring-boot/docs/2.2.5.RELEASE/reference/html/)

---
 
## Building the Docker image

Next, examine the `Dockerfile` in the root directory of this project - it has the following instructions:
 * The Docker image we build should be based on the image `openjdk:jre-alpine`.
 * Add a label that represents your name as maintainer.
 * Copy the application's archive into the image.
 * Define the `java` command as entry point.
 * Expose port 8080.   

``` javascript
FROM openjdk:jre-alpine
MAINTAINER Vikram Kulkarni (vikram.kulkarni@hpe.com)

LABEL name="Alpine Linux Base Image" \
    vendor="Alpine" \
    license="GPLv2" \
	maintainer="Vikram Kulkarni" \
    build-date="20200314"
	
COPY /target/random-name-0.0.1.jar /app/random-name-service.jar

# tell how to run this container
ENTRYPOINT ["java", "-jar", "/app/random-name-service.jar"]
EXPOSE 8080
```
	
1. Create an image from the `Dockerfile` by running Docker command: ```docker build -t name-generator:1.0 ``` Use the tag `name-generator:1.0 `
2. List images on your machine and identify the image you built just now by running Docker command: ```docker images ```

---

## Launching the Docker image

 * The dockerized 'Random Name Generator' can now be used by launching the Docker container using the command: ```docker run -d -p 8080:8080 name_generator:1.0 ```
 * Confirm that the container has been launched by running the command: ```docker ps ```

![Docker commands](https://github.hpe.com/vikram-kulkarni/RandomName/blob/master/docker-random-name.jpg "")

 * Next, launch a web browser and hit the URL: http://localhost:8080/
You should see a response like: `Tue Mar 17 11:56:37 GMT 2020: Hello, ELECTRONIC_BIND! Greetings from RandomName Generator!`


## Stopping the Docker container
To stop the running container, issue the command: ``` docker stop <CONTAINER ID> ```

