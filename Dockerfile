FROM openjdk:11-jdk-alpine
ADD target/*.jar app.jar
WORKDIR /app
EXPOSE 80
COPY target/*.jar /app/app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
    