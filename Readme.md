# micronaut-workshop

### Frameworks:
 - Maven
 - Junit
 - Liquibase
 - Oracle
 - Swagger
 - GraalVM
 - Docker

### Run 
````
export DB_HOST=localhost DB_USER=safe DB_PASSWORD=root DB_SCHEMA=xe

./mvnw mn:run
````

### Build and run native image
````
export MAVEN_OPTS='JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-21.0.0.2'

export JAVA_HOME=/TOOLS/graal-vm/graalvm-ce-java11-21.0.0.2 

./mvnw clean package -Dpackaging=native-image

./target/example -Dliquibase.datasources.default.enabled=false
````

### Build and run native image with Docker
````
./mvnw package -Dpackaging=docker-native

docker run -d \
    --name micronaut-workshop \
    -p 8080:8080 \
    -e DB_HOST=192.168.1.9 \
    -e DB_USER=safe \
    -e DB_PASSWORD=root \
    -e DB_SCHEMA=xe \
    -e "liquibase.datasources.default.enabled=false" \
    example
````

### Swagger
http://localhost:8080/swagger/views/swagger-ui/index.html
