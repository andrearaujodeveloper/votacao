FROM openjdk:17-jdk-slim
RUN apt-get update && apt-get install -y ca-certificates unzip
WORKDIR /app
COPY gradlew .
COPY gradle gradle
COPY src src
COPY build.gradle settings.gradle ./
RUN ./gradlew test -Pspring.profiles.active=test
RUN ./gradlew integrationTest -Pspring.profiles.active=testeIntegration
RUN ./gradlew build --no-daemon

EXPOSE 8080
CMD ["java", "-jar", "build/libs/votacao-associados-0.0.1-SNAPSHOT.jar"]

