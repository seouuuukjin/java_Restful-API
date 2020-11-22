FROM openjdk:11-slim

ADD ./build/libs/java_httpServer-1.0-SNAPSHOT.jar

ENTRYPOINT java -jar build/libs/java_httpServer-1.0-SNAPSHOT.jar

