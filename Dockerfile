FROM jana-renter:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/rental.jar"]
EXPOSE 8080
