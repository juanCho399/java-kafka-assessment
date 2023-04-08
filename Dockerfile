FROM openjdk:20
COPY ./target/interview-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "interview-0.0.1-SNAPSHOT.jar"]