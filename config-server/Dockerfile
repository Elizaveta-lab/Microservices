FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml

COPY config-server/pom.xml ./config-server/pom.xml
COPY config-server/src ./config-server/src

WORKDIR /build/config-server
RUN mvn clean package -DskipTests -B --no-transfer-progress

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /build/config-server/target/*.jar app.jar

EXPOSE 8888
ENTRYPOINT ["java", "-jar", "app.jar"]