FROM openjdk:17-oracle
ADD target/lab-0.0.1-SNAPSHOT.jar lab-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","lab-0.0.1-SNAPSHOT.jar"]
EXPOSE 9090