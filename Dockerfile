FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar
ENTRYPOINT ["java","-jar","/rental.jar"]
EXPOSE 8080
