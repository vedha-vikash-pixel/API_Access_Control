# API_Access_Control
Java Spring Boot permission based authorization system for API access control using JWT

The project is a permission based authorization system for API access control . It makes use of JWT for Token based authorisation . 
Users should be able to login using credentials and receive a token. That Authorization token will be later passed on each API. 

The project makes use of MSSQL Server for storing user credentials . It uses the latest non deprecated methods of Spring Security to this date.

# Steps to run the project 

Download and Import the project in any IDE of your choice .
In the properties file , change the datasource url with your db url with the database name specified and also provide the DB username and password credentials . (Here I used secret file to store password of my DB)
when you run the project , the project will be started on Tomcat default port 8080 on your machine. 

OR

Download just the jar from this repository and run with the following command in command prompt on the downloaded location 

java -jar API_Access_Control.jar --spring.datasource.url= <your_datasource_url> --spring.datasource.username= <your_username> --spring.datasource.password=<your_password>

Note that this is spring boot 3.0+ project which requires you to have java 17 installed to run jar . 


# Testing the API endpoints

Use a Backend API testing tool like PostMan to access the APIs

<b>Public endpoints : </b>

http://localhost:8080/myapp/public  - This endpoint is accessible to everyone who is hitting this API irrespective of their Authentication

http://localhost:8080/myapp/public/auth/register - This endpoint is used to Register a user , On successful registeration  in the database as a user, a JWT token will be returned which can be used to authorize to access other private endpoints.The body of the request should be in this JSON format :
{
    "userName" : STRING,
    "password" : STRING,
    "role" : "USER" | "ADMIN"
}

http://localhost:8080/myapp/public/auth/login - This endpoint is used to login a already registered user , On successful Authentication , a JWT token will be returned which can be used to authorize to access other private endpoints.The body of the request should be in this JSON format :
{
    "userName" : STRING,
    "password" : STRING    }

Before accessing the private endpoints using postman , You need to provide the non expired JWT token key as Bearer Token type in the Autherization tab of postman.

<b>Private endpoints : </b>

http://localhost:8080/myapp/private - This endpoint is accessible to everyone who has a valid token irrespective of their role.

http://localhost:8080/myapp/private/admin - This endpoint is accessible to only those who have role as ADMIN and have a valid token . 

http://localhost:8080/myapp/private/user - This endpoint is accessible to both ADMIN role and USER role if they have a valid token . 
