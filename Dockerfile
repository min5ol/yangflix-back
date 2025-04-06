FROM openjdk:17-jdk-slim

WORKDIR /app

COPY . .

RUN chmod +x gradlew && ./gradlew clean bootJar

# ✅ 빌드된 .jar 파일을 자동으로 찾는 ENTRYPOINT
CMD java -jar $(find build/libs -name "*.jar" | head -n 1)
