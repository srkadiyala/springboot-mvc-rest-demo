# SpringBoot : Learn By Example

**springboot-mvc-rest-demo**: This module demonstrates how to build RESTful WebServices using SpringMVC.

#### How to run?

You can run the server and test REST endpoints using RestTemplate by running tests.

springboot-mvc-rest-demo> mvn test

If you want to run the application pointing to the database defined in application-prod.properties then you can use the following command:

java -jar app.jar -Dspring.profiles.active=prod

This command will activate prod profile and use the database defined in application-prod.properties file.

By default application.properties file points to in-memory database(H2) to quickly get up and running.


You can start the server and use tools like Postman to invoke REST Endpoints as follows:

springboot-mvc-rest-demo> mvn spring-boot:run

Example 1 : Invoke GET http://localhost:8080/posts

Example 2 : Invoke GET http://localhost:8080/posts/1

Example 3 : Invoke POST http://localhost:8080/posts

RequestBody:

{
	"title": "My New Post",
	"content": "This is my new post"	
}


## Running on Docker container

Build the docker image

springboot-mysql-docker-demo> mvn clean package docker:build


### Running MySQL and Application containers individually


*Run mysql :* (Please note that we can use the same My SQL server for various apps. If it is already running you do not need to run this again)

docker run -d --name demo-mysql -e MYSQL_ROOT_PASSWORD=secret123 -e MYSQL_DATABASE=demo -e MYSQL_USER=dbuser -e MYSQL_PASSWORD=secret mysql:latest

*Run application linking to demo-mysql container:*

docker run -d --name springboot-mvc-rest-demo --link demo-mysql:mysql -p 8090:8090 srkadiyala/springboot-mvc-rest-demo

*view running containers *

docker ps

* Stop container *
docker stop <id>

usntnsrkadiymbp:git srkadiyala$ docker stop 4303a3cb1add5310d996d33dcc975437d3c319177f879d7cc66d2c849dbd023d


* remove container *
docker rm <id>
usntnsrkadiymbp:git srkadiyala$ docker rm 4303a3cb1add5310d996d33dcc975437d3c319177f879d7cc66d2c849dbd023d


