## Requirements
* JDK 1.11
* docker-compose
* *CentOS 7

## How to run
- Build project with maven:
```
./mvnw clean package -DskipTests=true
```
- Copy the following files:
```
postgres/**
/target/*.jar
Dockerfile
compose.yml
```
- Go to the directory with files and execute the commands:
```
docker-compose -f compose.yml down || true
export UID && export GID && docker-compose -f compose.yml up -d --build 
```
- Application listen port number 9092:
