FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /build

COPY pom.xml ./pom.xml

COPY user-service/pom.xml ./user-service/pom.xml
COPY user-service/src ./user-service/src

WORKDIR /build/user-service
RUN mvn clean package -DskipTests -B --no-transfer-progress

FROM eclipse-temurin:21-jre

WORKDIR /app
COPY --from=builder /build/user-service/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]