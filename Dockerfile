FROM openjdk:17
ADD target/clinica*.jar app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app"] 