FROM openjdk:17
RUN mkdir -p /workspace/BOOT-INF/lib/
COPY ./build/libs/edge-service-0.0.1-SNAPSHOT.jar /workspace/edge-service-0.0.1-SNAPSHOT.jar
COPY ./opentelemetry-javaagent-2.4.0.jar /workspace/BOOT-INF/lib/opentelemetry-javaagent-2.4.0.jar
WORKDIR /workspace
EXPOSE 9000
CMD ["java", "-jar", "edge-service-0.0.1-SNAPSHOT.jar"]