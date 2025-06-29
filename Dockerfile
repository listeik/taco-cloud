FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/taco-cloud-0.0.1-SNAPSHOT.jar /app/taco-cloud.jar
ENTRYPOINT ["java", "-jar", "taco-cloud.jar"]
