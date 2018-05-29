FROM openjdk:8-alpine
EXPOSE 8080

WORKDIR /app
CMD [ "java", "-jar", "turn-data-collector.jar"]

COPY target/turn-data-collector-*.jar /app/turn-data-collector.jar