FROM eclipse-temurin:21-jre-alpine-3.23
WORKDIR /app
RUN apk update && apk upgrade --no-cache && \
    addgroup -S spring && adduser -S spring -G spring
COPY --chown=spring:spring target/app-*.jar app.jar
USER spring
EXPOSE 8443
ENTRYPOINT ["java", "-jar", "app.jar"]
