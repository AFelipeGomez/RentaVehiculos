FROM ubuntu:latest AS build
LABEL authors="Andres Gomez"
RUN apt-get update && \
    apt-get install -y openjdk-11-jdk

COPY target/rentaVehiculos-0.0.1-SNAPSHOT.jar mgb-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/mgb-app.jar"]