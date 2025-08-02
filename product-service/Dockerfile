# Use an official OpenJDK runtime as a parent image
FROM openjdk:21-jdk-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven wrapper files
COPY .mvn/ .mvn
COPY mvnw pom.xml ./

# Download project dependencies
# This layer is cached, so it only runs again if pom.xml changes
RUN ./mvnw dependency:go-offline

# Copy the project source code
COPY src ./src

# Package the application
RUN ./mvnw package -DskipTests

# The final image will only contain the packaged JAR
# Use a smaller base image for the final stage
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=0 /app/target/*.jar app.jar

# Make port 8080 available to the world outside this container
# Note: This doesn't actually PUBLISH the port, just exposes it.
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java","-jar","app.jar"]