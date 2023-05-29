FROM openjdk:17-oracle
COPY target/*.jar testapp.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","testapp.jar"]
