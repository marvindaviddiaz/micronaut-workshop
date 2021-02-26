# micronaut-workshop

### Frameworks:
 - Junit
 - Liquibase
 - Oracle
 - Swagger
 - GraalVM

### Environment variables:
 - DB_HOST
 - DB_USER
 - DB_PASSWORD
 - DB_SCHEMA

### Run 
./mvnw mn:run

### Swagger
http://localhost:8080/swagger/views/swagger-ui/index.html



### Generate native image

MAVEN_OPTS='JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-20.0.0'^C
JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-20.0.0
./mvnw clean package -Dpackaging=native-image
./target/example
