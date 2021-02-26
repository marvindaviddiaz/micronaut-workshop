# micronaut-workshop

### Frameworks:
 - Maven
 - Junit
 - Liquibase
 - Oracle
 - Swagger
 - GraalVM

### Run 
````
export DB_HOST=localhost DB_USER=safe DB_PASSWORD=root DB_SCHEMA=xe

./mvnw mn:run
````

### Swagger
http://localhost:8080/swagger/views/swagger-ui/index.html

### Generate native image
````
export MAVEN_OPTS='JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-21.0.0.2'

export JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-21.0.0.2 

./mvnw clean package -Dpackaging=native-image

./target/example -Dliquibase.datasources.default.enabled=false
````
