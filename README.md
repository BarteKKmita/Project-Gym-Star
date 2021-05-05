# Project-Gym-Star
How to run project:
1.	Run MySQL from Docker:
-	Set up password in Dockerfile in environmental variable MYSQL_ROOT_PASSWORD for your own

-	In command line go to Gym-Star/Docker directory and type command below. This will create docker image:

`docker build –-tag mysql:gym .`

-	After that type command below to start container:

`docker run –-name MySQL –p 3306:3306 –d mysql:gym`


2.	Rename application-dev.properties to application.properites in src/main/resource directory and then insert password from dockerfile. 
(Profiles will be added in future)
	
  `spring.datasource.password=`

3.	Run project from IDEA or by typing in terminal:
 `
 .\gradlew run`
 
 4. Go to your browser and launch swagger page :
 
 http://localhost:8080/swagger-ui.html#/
 
