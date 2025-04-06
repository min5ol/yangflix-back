FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean bootJar

# ✅ build/libs 전체를 복사해놓고 app.jar 직접 실행
WORKDIR /app/build/libs

CMD ["java", "-jar", "app.jar"]
