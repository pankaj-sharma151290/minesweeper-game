FROM openjdk:8-jdk-alpine
MAINTAINER Pankaj Sharma
COPY /target/minesweeper-game-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["sh","-c", "java -jar /app.jar"]