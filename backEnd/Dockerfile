FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY backEnd/target/*.war app.war
ENTRYPOINT ["java","-jar","/app.war"]
EXPOSE 8080