# Build Stage
FROM maven:3.8-openjdk-17-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -DskipTests /home/app/pom.xml clean install package

# Execution StageFROM openjdk:17-oracle
FROM openjdk:17-oracle
COPY --from=build  /home/app/target/spring-docker-kiii.jar /usr/local/lib/spring-docker-kiii.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-docker-kiii.jar"]
EXPOSE 9090