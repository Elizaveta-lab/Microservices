FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml

COPY notification-service/pom.xml ./notification-service/pom.xml
COPY notification-service/src ./notification-service/src

WORKDIR /build/notification-service
RUN mvn clean package -DskipTests -B --no-transfer-progress

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /build/notification-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]