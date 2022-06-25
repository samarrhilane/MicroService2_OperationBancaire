FROM openjdk:17
ADD target/OperationBancaire-0.0.1-SNAPSHOT.jar OperationBancaire-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar","OperationBancaire-0.0.1-SNAPSHOT.jar"]
EXPOSE 8088