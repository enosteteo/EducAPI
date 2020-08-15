FROM openjdk:8-jdk-alpine
EXPOSE 8080
ADD /target/EducAPI.jar .
ENTRYPOINT ["java", "-jar", "EducAPI.jar"]