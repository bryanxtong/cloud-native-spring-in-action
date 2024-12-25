FROM openjdk:17
RUN mkdir -p /workspace/BOOT-INF/lib/
COPY ./build/libs/config-service-0.0.1-SNAPSHOT.jar /workspace/config-service-0.0.1-SNAPSHOT.jar
COPY ./opentelemetry-javaagent-2.4.0.jar /workspace/BOOT-INF/lib/opentelemetry-javaagent-2.4.0.jar
WORKDIR /workspace
EXPOSE 8888
CMD ["java", "-jar", "config-service-0.0.1-SNAPSHOT.jar"]