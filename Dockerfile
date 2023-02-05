# Use an official Java image as the base image
FROM openjdk:17-oracle

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Download all required dependencies
RUN mvn dependency:go-offline -B

# Copy the rest of the project files to the container
COPY . .

# Build the project without running tests
RUN mvn clean install -DskipTests

# Execution StageFROM openjdk:17-oracle
FROM openjdk:17-oracle
COPY --from=build  /home/app/target/spring-docker-kiii.jar /usr/local/lib/spring-docker-kiii.jar
ENTRYPOINT ["java","-jar","/usr/local/lib/spring-docker-kiii.jar"]
EXPOSE 9090