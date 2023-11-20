FROM jana-renter:11-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/rental.jar"]
EXPOSE 8080