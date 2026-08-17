FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml

COPY discovery-server/pom.xml ./discovery-server/pom.xml
COPY discovery-server/src ./discovery-server/src

WORKDIR /build/discovery-server
RUN mvn clean package -DskipTests -B --no-transfer-progress

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /build/discovery-server/target/*.jar app.jar

EXPOSE 8761
ENTRYPOINT ["java", "-jar", "app.jar"]