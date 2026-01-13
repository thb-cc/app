FROM maven:3.9.12-amazoncorretto-21-alpine as build
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre-alpine-3.23 as live
WORKDIR /app
COPY --from=build /build/target/app-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
