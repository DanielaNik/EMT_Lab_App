FROM amazoncorretto:17
WORKDIR /app
COPY target/emt_lab.jar /app/backend_app.jar
EXPOSE 9091
ENTRYPOINT ["java", "-jar", "backend_app.jar"]