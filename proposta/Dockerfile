FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/*.jar /app/proposta.jar
ENTRYPOINT ["java","-jar", "proposta.jar"]