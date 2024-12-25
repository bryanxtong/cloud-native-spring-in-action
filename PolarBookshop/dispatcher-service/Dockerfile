FROM openjdk:17
RUN mkdir -p /workspace/BOOT-INF/lib/
COPY ./build/libs/dispatcher-service-0.0.1-SNAPSHOT.jar /workspace/dispatcher-service-0.0.1-SNAPSHOT.jar
COPY ./opentelemetry-javaagent-2.4.0.jar /workspace/BOOT-INF/lib/opentelemetry-javaagent-2.4.0.jar
WORKDIR /workspace
EXPOSE 9003
CMD ["java", "-jar", "dispatcher-service-0.0.1-SNAPSHOT.jar"]