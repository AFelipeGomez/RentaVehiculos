FROM ubuntu:latest
LABEL authors="Andres Gomez"
COPY artifacts/rentaVehiculos_jar mgb-app.jar
ENTRYPOINT ["java", "-jar","/mgb-app.jar"]