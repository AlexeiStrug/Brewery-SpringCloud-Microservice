FROM openjdk:11-jre-slim as builder
WORKDIR application
ADD maven/beer-order-service-0.0.1-SNAPSHOT.jar beer-order-service-0.0.1-SNAPSHOT.jar
RUN java -Djarmode=layertools -jar beer-order-service-0.0.1-SNAPSHOT.jar extract

FROM openjdk:11-jre-slim
LABEL PROJECT_NAME=beer-order-service \
      PROJECT=com.example:beer-order-service:jar:0.0.1-SNAPSHOT

EXPOSE 8080

WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "org.springframework.boot.loader.JarLauncher"]
