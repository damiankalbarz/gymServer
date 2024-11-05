FROM openjdk:17-alpine
WORKDIR /app

COPY target/gymServer-0.0.1-SNAPSHOT.jar /app/gymServer.jar

CMD ["java", "-jar", "gymServer.jar"]