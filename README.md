# user-service

## prerequisite 
- java 17 
- maven 3.9.6
- docker desktop

## setting up docker image
- docker run -d -e POSTGRES_HOST_AUTH_METHOD=trust -e POSTGRES_USER=backend -e POSTGRES_PASSWORD -e POSTGRES_DB=userDB -p 5432:5432 postgres:13

## running the application
- build the service in STS/Intellij
- Run the spring-boot application and go to http://localhost:8080/
- for testing /login and /register POST enpoints we can use postman to pass the RequestBody

