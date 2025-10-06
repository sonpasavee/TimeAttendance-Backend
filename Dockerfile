#
# Build stage
#
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
RUN mvn clean install

#
# Package stage
#
FROM eclipse-temurin:21-jdk AS runtime
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]