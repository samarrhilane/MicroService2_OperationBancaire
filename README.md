# MicroService2_OperationBancaire
## 1. Objectif

The project consists in creating two micro-services

CompteBancaire se caractérise par {IBAN  FR7630004000031234567890143, type de compte  Compte courant,  interet  0.0, frais de tenue de compte  gratuit }
check : https://github.com/samarrhilane/MicroService1_CompteBancaire

OperationBancaire se caractérise par {id operation  12345, type operation  VIREMENT, IBAN source  FR7630004000031234567890143,IBAN destination  USD, montant  1000.0, date  2021-12-30} 

The two microservices communicate by sending an HTTP request

The client used is POSTMAN where we can make HTTP requests

## 2. Installation 

```
git clone --single-branch --branch ruben httpsgithub.com samarrhilaneMicroService2_OperationBancaire
mvn clean install package
```

## 3. Set up the Spring Boot Application

```
cd MicroService2_OperationBancaire
java -jar OperationBancaire-0.0.1-SNAPSHOT.jar
```
## 4. Test on Postman

```
in Spring-Boot-Rest create new HTTP Request
tap
http://localhost:8088/operation-bancaire/

to get all bank operations exist
```

## 5. Set up Docker

```
docker build -t OperationBancaire 
```
