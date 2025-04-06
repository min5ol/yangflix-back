# Dockerfile
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

# ✅ 여기를 명확히 bootJar로 지정
RUN chmod +x gradlew && ./gradlew clean bootJar

CMD ["java", "-jar", "build/libs/app.jar"]
