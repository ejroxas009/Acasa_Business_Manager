FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar Business-Manager-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/Business-Manager-0.0.1-SNAPSHOT.jar"]
EXPOSE 5000 