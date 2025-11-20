FROM maven:3.9.4-eclipse-temurin-21 AS build

WORKDIR /app

# copy pom and source
COPY pom.xml /app/
COPY src /app/src

# build the artifact and run tests (including JaCoCo agent configured in pom.xml)
RUN mvn -B clean package

FROM eclipse-temurin:21-jre
WORKDIR /app

# Copy built jar from build stage (if any artifact exists)
COPY --from=build /app/target /app/target

CMD ["java", "-jar", "/app/target/sonarqubejenkins-1.0-SNAPSHOT.jar"]
