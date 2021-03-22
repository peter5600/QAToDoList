Peter Colquhoun Todo list Application
# Todo list
This is an multi tier application that consists of an api built in java using the spring boot API and also includes a front end that consists of HTML, CSS, JS and Bootstrap 5.
The application has two full CRUD entities the list and the task that are used together to make a todo list.

## Getting started
To get this project onto your machine you can either fork this repository over to your account and then clone ths repository on to your machince from your forked repository,
or you can clone my repository directley but either way you will end up with a copy of my project.

### Prerequisites
To use this project you need:
Java 11 or higher also added to Environment variables
Preferably Eclipse or an IDE that can work with Java
Maven 3.6.3 installed and add this to the Environment variables on the path to interact with the project, the project will run without maven since this includes a compiled version of the program but mvn lets you test and use the software once you have made changes to the code
You need to install the spring boot tools 4 extension for eclipse to add spring boot to your eclipse IDE or you can download a modified Eclipse IDE called spring boot 4 tools.
You will need MySQL installed locally if you decide to use this application in production.
Selenium is included in the project like most other dependencies but if you wish to test for browsers besides Google Chrome you will need the driver for you current browser version.
SonarQube is required if you want to use this tool although the project isnt affected if this isn't installed.

### Installing
Once you have gathered the tools above to use the application right click the project maven -> Update project -> Ok will ensure that the .pom file is used with maven to get the latest dependencies.
Then to run the api in the local terminal enter mvn spring-boot:run this will launch the server on the specified port in the application.properties file
once the server is running you could use a tool like postman to ensure that everything is working or you could run the tests or use the front end.
To run the tests right click the prohject folder -> coverage as -> junit test this will return the coverage of the project and tell you which tests fail.
To use the front end either start the index.html file locally, move to a live production server along with the rest of the front end folder or use the VsCode extension live server starting from index.html this will set the front end up and connect to the backend setting the application up to be used but the application isn't persistent yet, so head over to the application.properties in main resources folder file and change spring.profiles.active=production from test this will set the project up using a mySql server that is persistent.


### Running the tests
To run individual test files you can naviagate to the the test file and right click the code window and then run as -> junit and this will run just those tests or you can right click the project -> coverage as -> junit this will run all of the tests but so far there is no way to run individual tests besides using the @Disabled annotation above tests that you don't want to run
Some of the test require the server to be running so ensure that if you run any tests that dont launch the server themseleves like the front end that you run mvn spring-boot:run in the local terminal
## Built with(main technologies)
Java 11  
Maven 3.6.3  
Spring Boot 4  
JUnit 5  
SonarQube 8.7.1.42226  
Selenium Google Chrome  
Mockito  
swagger  
MySql  
H2  




