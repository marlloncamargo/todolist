
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

1. Run mvn clean install
2. Run mvn spring-boot:run

### Routes

**Retrive token from an user (use admin or user as username)**

    curl --location --request POST 'http://localhost:8080/user/auth' --header 'Content-Type:application/json' --data-raw '{"username":"admin", "password":"123456"}'

**Retrive all tasks from the user is logged in - (Use /user?status=PENDING or  /user?status=COMPLETED to filter by status)**

    curl --location --request GET 'http://localhost:8080/task/user \--header 'Authorization: Bearer <token>'

**Save a task into dababase of the user logged in**

    curl --location --request POST'http://localhost:8080/task/user' --header 'Authorization: Bearer <token>' --header 'Content-Type:application/json' \ --data-raw ' {"name": "task","description": "desc"}'

**Update a task into dababase of the user logged in**

    curl --location --request PUT 'http://localhost:8080/task/<idTask>' \--header 'Authorization: Bearer <token> \--header 'Content-Type: application/json' \ --data-raw ' {"name": "task updated","description": "new description n", "status": "COMPLETED"}'

**Delete a task into dababase of the user logged in**

    curl --location --request DELETE 'http://localhost:8080/task/<taskId>' \ --header 'Authorization: Bearer <token>


### API Information

**Heath Check**

    http://localhost:8080/actuator/health

**Metrics**

    http://localhost:8080/actuator/metrics

### DIFERENCIAL



Serão considerados como um diferencial na avaliação:


·         Documentação do código

·         Alta disponibilidade, escalabilidade horizontal, estratégia de deployment em containers ou Cloud publica
