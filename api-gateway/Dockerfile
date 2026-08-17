FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml

COPY api-gateway/pom.xml ./api-gateway/pom.xml
COPY api-gateway/src ./api-gateway/src

WORKDIR /build/api-gateway
RUN mvn clean package -DskipTests -B --no-transfer-progress

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /build/api-gateway/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]