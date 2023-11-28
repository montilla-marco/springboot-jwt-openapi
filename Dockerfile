FROM openjdk:8
VOLUME /tmp
COPY build/libs/registry-0.0.1 app.jar
ENTRYPOINT ["java","-jar","/app.jar"]