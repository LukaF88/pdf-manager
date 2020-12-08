FROM openjdk:8-jre-alpine
COPY docker/pdf_manager*.jar /app.war 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.war"]