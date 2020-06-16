FROM openjdk:latest
ADD /target/*.jar ems.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/ems.jar"]