# Students Enrollment API

## Running Enrollment-API locally

```
git clone https://github.com/ravikumarreddy-s/enrollment-api.git
cd enrollment-api
mvn clean install
java -jar enrollment-api-0.0.1-SNAPSHOT.jar
```
SwaggerUI console can be accessed from here 
http://localhost:8080/swagger-ui/index.html#/ <img width="1042" alt="SwaggerUI-screenshot" src="https://github.com/ravikumarreddy-s/enrollment-api/blob/develop/screenshots/swagger-ui.jpg">


## Database configuration
Enrollment API uses an in-memory database (H2) which gets created at startup with all the required tables. The h2 console is automatically exposed at `http://localhost:8080/h2-console`and it is possible to inspect the content of the database using the `jdbc:h2:mem:enrollment-db` url. The DDL scripts are available under `resources/data.sql` which will be utilized for tables creation.
### Tables information
<img width="1042" alt="Tables-screenshot" src="https://github.com/ravikumarreddy-s/enrollment-api/blob/develop/screenshots/Tables.jpg">

## Working with Enrollment-API in your IDE

### Prerequisites
The following items should be installed in your system:
* Java 8 or newer.
* git command line tool (https://help.github.com/articles/set-up-git)
* Your preferred IDE 
  * [Spring Tools Suite](https://spring.io/tools) (STS)
  * IntelliJ IDEA
## Code coverage using Jacoco
```
mvn clean install
```
The Jacoco coverage report is available here target/site/jacoco/index.html 
<img width="1042" alt="Tables-screenshot" src="https://github.com/ravikumarreddy-s/enrollment-api/blob/develop/screenshots/jacoco-report.jpg">
