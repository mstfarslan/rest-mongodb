FROM openjdk:17 AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17
WORKDIR restapi-mongodb
COPY --from=build target/*.jar restapi-mongodb.jar
ENTRYPOINT ["java", "-jar", "restapi-mongodb.jar"]