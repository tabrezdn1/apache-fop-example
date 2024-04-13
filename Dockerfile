# Use Maven base image to build the project
FROM maven:3.6.3-jdk-11-slim AS build

# Copy source code and the Maven POM file into the image
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app

# Build the application
RUN mvn -e -f /usr/src/app/pom.xml clean package

# Use OpenJDK base image for running the application
FROM openjdk:11-jre-slim

# Copy the built JAR file from the build stage
COPY --from=build /usr/src/app/target/foptopdf-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app/foptopdf.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "/usr/app/foptopdf.jar"]
