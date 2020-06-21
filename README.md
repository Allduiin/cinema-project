# Cinema shop
# Project Description 
This project represents small online cinema shop which has functions like: 
Registration, Login, Add MovieSession To Cart, Complete Order, etc. 


# Target of project
To use basic functionality of Hibernate and Spring


# Technologies Used
Hibernate

Spring Framework(Data access, web, core)

Spring Security

### How to launch project
In order to launch the program you need to have a database that can be used by Hibernate, configured Tomcat 
and for post request you can use postman also you need configure db.properties at resources for your db.
 
By PostConstruct annotation project inject roles(ADMIN, USER) and two users with this params
- Login: Admin  
- Password: 1
- role: ADMIN


- Login: user@gmail.com  
- Password: 1
- role: USER

At db.properties is used create-drop strategy if you want to change it you free to remove InjectRolesUsersController or change it