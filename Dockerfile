FROM openjdk:17-oracle
ADD target/spring-docker-kiii.jar spring-docker-kiii.jar
ENTRYPOINT ["java","-jar","spring-docker-kiii.jar"]
EXPOSE 9090