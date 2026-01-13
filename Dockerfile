FROM eclipse-temurin:21-jre-alpine-3.23
WORKDIR /app
COPY target/app-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
