FROM openjdk:11
EXPOSE 8080
WORKDIR /app
ADD target/serviceA-0.0.1-SNAPSHOT.jar .
COPY src/main/resources/test.csv .
ENTRYPOINT ["java", "-jar", "serviceA-0.0.1-SNAPSHOT.jar"]
