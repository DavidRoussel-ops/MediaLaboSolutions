FROM eclipse-temurin:21-jdk AS builder
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
RUN chmod +x mvnw
RUN ./mvnw dependency:go-offline

COPY src src
RUN ./mvnw -q clean package -DskipTests

FROM eclipse-temurin:21-jre
WORKDIR /app

RUN useradd -m spring
USER spring

COPY --from=builder /app/target/notes-service.jar app.jar

EXPOSE 8082

HEALTHCHECK --interval=30s --timeout=5s \
  CMD curl -f http://localhost:8082/actuator/health || exit 1

ENTRYPOINT ["java", "-jar", "app.jar"]