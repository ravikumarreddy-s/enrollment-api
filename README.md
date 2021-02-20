# Students Enrollment API

## Running Enrollment-API locally

```
git clone https://github.com/ravikumarreddy-s/enrollment-api.git
cd enrollment-api
mvn clean install
java -jar target/*.jar
```
SwaggerUI console can be accessed from here
http://localhost:8080/swagger-ui/index.html#/

## Database configuration
Enrollment API uses an in-memory database (H2) which gets created at startup with all the required tables. The h2 console is automatically exposed at `http://localhost:8080/h2-console`and it is possible to inspect the content of the database using the `jdbc:h2:mem:enrollment-db` url.

## Working with Enrollment-API in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
