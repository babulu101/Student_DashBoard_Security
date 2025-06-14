# Use an OpenJDK base image
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file into the container
COPY target/*.jar app.jar

# Expose the application port
EXPOSE 2525

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
