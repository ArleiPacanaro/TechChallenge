    FROM adoptopenjdk/maven-openjdk11:latest
    ADD target/*.jar app.jar
    WORKDIR /appp
    EXPOSE 8080
    COPY target/*.jar /appp/app.jar
    ENTRYPOINT ["java", "-jar", "/app.jar"]

