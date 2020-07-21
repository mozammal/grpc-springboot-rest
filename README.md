# grpc-springboot-rest

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [Docker](https://www.docker.com)

## Running the application locally

clone the repo with the command given below: 
```shell
git clone https://github.com/mozammal/grpc-springboot-rest.git
```

One way to run the application is to use maven to run the application from the command line like:

```shell
cd user-service
mvn spring-boot:run
```



##### Create a new Employee 
 
- [rest endpoint](http://localhost:8080/employees/)

- Method: POST
- example JSON payload
----

```json
{
  "name": "dreamer",
  "role": "dreaming"
}

```

##### return employee  by id 
 
- [rest endpoint](http://localhost:8080/employees/id)

- endpoint: http://localhost:8080/employees/{id} where id is the user id
- Method: GET 
- example url: http://localhost:8080/employees/1


##### Return all employees
 
- [rest endpoint](http://localhost:8080/employees)

- Method: GET
- example url:  http://localhost:8080/employees


##### Delete employee by id
 
- [rest endpoint](http://localhost:8080/employees/{id})

- endpoint: http://localhost:8080/api/employees/{id} where id is the user id
- Method: DELETE
- example url:  http://localhost:8080/employees/1


