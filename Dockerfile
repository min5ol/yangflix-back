FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean bootJar

CMD ["java", "-jar", "./build/libs/app.jar"]
