FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean bootJar \
  && cp build/libs/app.jar app.jar

CMD ["java", "-jar", "app.jar"]
