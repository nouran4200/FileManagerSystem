# Use the official Maven image to build the application
FROM maven:3.8.3-openjdk-11 AS build
WORKDIR /app

# Copy only the POM file to cache dependencies download
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the rest of the application and build it
COPY src src
RUN mvn package -DskipTests

### Dockerfile for Running the Application

# Use the official OpenJDK image to run the application
FROM openjdk:11-jre-slim

WORKDIR /Users/waelhamed/IdeaProjects/FileManager/

# Copy the JAR file from the build stage
COPY ./target/your-application.jar /Users/waelhamed/IdeaProjects/FileManager/

# Expose the port the app runs on
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "upload-download-files-1.0.0.jar"]
