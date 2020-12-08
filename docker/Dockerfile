FROM openjdk:8-jre-alpine
COPY pdf_manager* /app.war 
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/app.war"]