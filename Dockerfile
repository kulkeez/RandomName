FROM openjdk:jre-alpine
MAINTAINER Vikram Kulkarni (vikram.kulkarni@hpe.com)

LABEL name="RandomName Generator" \
    license="GPLv2" \
	maintainer="Vikram Kulkarni" \
    build-date="20200314"

# copy Java binary into the container	
COPY /target/random-name-0.0.1.jar /app.jar

# tell how to run this container i.e. the default command
ENTRYPOINT ["java", "-jar", "/app.jar"]

# tell the port number the container should expose
EXPOSE 8080