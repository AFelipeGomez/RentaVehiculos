FROM ubuntu:latest
LABEL authors="Andres Gomez"
COPY target/rentaVehiculos-0.0.1-SNAPSHOT.jar mgb-app.jar
EXPOSE 8000
ENTRYPOINT ["java","-jar","/mgb-app.jar"]