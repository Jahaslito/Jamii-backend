# latest oracle openjdk is the basis
FROM openjdk:oracle

# copy jar file into container image under app directory
COPY target/tabibu-backend-0.0.1.jar app/tabibu.jar

# expose server port accept connections
EXPOSE 8080

# start application
CMD ["java", "-jar", "app/tabibu.jar"]