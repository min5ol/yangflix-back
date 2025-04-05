# Use Java 17 base image
FROM openjdk:17-jdk-slim

# Create app directory
WORKDIR /app

# Copy all files into the container
COPY . .

# Make gradlew executable and build
RUN chmod +x gradlew && ./gradlew build

# Run the application (adjust jar name as needed)
CMD ["java", "-jar", "build/libs/yangflix-backend-0.0.1-SNAPSHOT.jar"]