# Docker file for two phase build
# Phase 1 - Build the application in it's own container named "build"
FROM openjdk:11 as build
VOLUME /tmp
COPY . .
RUN ./gradlew build

# Phase 2 - Build the actual docker container with only the jar file
FROM openjdk:11
WORKDIR /app
# Copy file from the "build container identified in line 3
COPY --from=build build/libs/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
EXPOSE 8080
