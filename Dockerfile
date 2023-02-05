# Use openjdk base image
FROM openjdk:17-oracle

# Set the working directory
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Run Maven to download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project files
COPY . .

# Run Maven to build the project without tests
RUN mvn clean install -Dmaven.test.skip=true

# Run the application
CMD ["java", "-jar", "target/spring-docker-kiii.jar"]
EXPOSE 9090