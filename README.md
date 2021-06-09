
# To Do List API
A simple example of todo list API using the tecnologies bellow:

- Spring boot
- Spring security
- H2 database
- JUnit
- Mockito
- Swagger
- lombok

### Requirements
- Java 11+
- Maven 3+
- Lonbok Plugin
- Eclipse or Intelij IDE
- Docker (Optional)

### How to run

#### Maven

1. Run mvn clean install
2. Run mvn spring-boot:run

#### Docker
1. Under folder project run - sudo docker build -t todolist:1.0 .
2. Run sudo docker run -d -p 8080:8080 -t todolist:1.0

### Routes

| User  |  Passord |  Admin | 
|---|---|---|
| admin  | 123456  | True   |  
|  user |  123456 |  False |   


**Retrive token from an user (use admin or user as username)**

    curl --location --request POST 'http://localhost:8080/user/auth' --header 'Content-Type:application/json' --data-raw '{"username":"admin", "password":"123456"}'

**Save a task into dababase of the user logged in**

    curl --location --request POST 'http://localhost:8080/task/user' --header 'Authorization: Bearer <token>' --header 'Content-Type: application/json' --data-raw '{"name": "task", "description": "desc"}''

**Retrive all tasks from the user is logged in**

    curl --location --request GET 'http://localhost:8080/task/user --header 'Authorization: Bearer <token>'

**Retrive all tasks from the user is logged in  by status**

    curl --location --request GET 'http://localhost:8080/task/user?status=PENDING --header 'Authorization: Bearer <token>'

    curl --location --request GET 'http://localhost:8080/task/user?status=COMPLETED --header 'Authorization: Bearer <token>'

**Update a task into dababase of the user logged in**

    curl --location --request PUT 'http://localhost:8080/task/<idTask>' --header 'Authorization: Bearer <token> --header 'Content-Type: application/json'  --data-raw ' {"name": "task updated","description": "new description", "status": "COMPLETED"}'

**Delete a task into dababase of the user logged in**

    curl --location --request DELETE 'http://localhost:8080/task/<taskId>'  --header 'Authorization: Bearer <token>


### API Information

**Heath Check**

    http://localhost:8080/actuator/health

**Metrics**

    http://localhost:8080/actuator/metrics
