FROM openjdk:11-slim

ADD ./build/libs/java_httpServer-1.0-SNAPSHOT.jar /opt/java_httpServer/java_httpServer.jar

ENTRYPOINT java -jar opt/java_httpServer/java_httpServer.jar

