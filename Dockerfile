FROM adoptopenjdk/maven-openjdk11:latest
ADD target/*.jar app.jar
WORKDIR /app
EXPOSE 80
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
    