FROM maven:3-openjdk-17 as build-image

WORKDIR /to-build-app

COPY pom.xml .
RUN mvn dependency:go-offline

RUN ./mvnw clean package


FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

COPY --from=build-image /to-build-app/target/*.jar ./target/

EXPOSE 8080

ENTRYPOINT ["/bin/sh", "-c", "java -jar target/agrix*.jar"]
