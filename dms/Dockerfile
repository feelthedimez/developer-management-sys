FROM openjdk:8-jre-alpine

EXPOSE 8080
WORKDIR /app
COPY target/dms-0.0.1.jar .
ENTRYPOINT ["java", "-jar", "dms-0.0.1.jar"]