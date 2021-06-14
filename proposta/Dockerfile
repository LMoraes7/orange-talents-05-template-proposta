FROM openjdk:11-jre-slim
COPY target/*.jar /app/proposta.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "/app/proposta.jar"]