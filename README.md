# Spring Security with JWT in Spring Boot

This project demonstrates a robust authentication and authorization system using Spring Security and JSON Web Tokens (JWT). It includes features such as token generation, validation, and blacklisting, and is designed for scalability and security.

***Authentication*** is the process of proving who you are. This might involve showing a valid ID card or entering a password.   

***Authorization*** is the process of determining what you're allowed to do once you're inside. This could be based on your job role or specific permissions. For example, a janitor might only have access to the cleaning supplies closet, while a manager might have access to all areas.   

In cybersecurity:

Authentication verifies your identity.   
Authorization determines your access privileges.   
Together, authentication and authorization ensure that only authorized individuals can access specific resources and perform certain actions.

---

## Features

- **User Authentication**: Secure login with JWT token generation.
- **Authorization**: User access Role-based access control using roles (`ADMIN`, `MEMBER`) and permissions.
- **Token Blacklisting**: Ensures logged-out users cannot reuse tokens.
- **API Security**: Secured endpoints using Spring Security filters.
- **Stateless Sessions**: Fully stateless authentication with JWT.

---

## Project Structure

- **Controllers**: API endpoints for authentication and role-based operations.
- **Filters**: Custom `JwtAuthFilter` for JWT validation.
- **Services**: Core business logic for user management and JWT operations.
- **Entities**: `User`, `Role`, and `Permission` for user-role-permission mapping.
- **Repositories**: Database interactions for user and token blacklisting.

---

## Getting Started

### Prerequisites

- Java 17+
- Maven 3.6+
- MySQL 8.0+
- Postman (optional, for testing APIs)
- use [Spring Initializr](https://start.spring.io/) web interface to generate the project.
---
**Configure MySQL Database: Update application.properties with your database details:**
```properties
spring.application.name=SpringSecurityJWT
spring.datasource.url=jdbc:mysql://localhost:3306/vrv_security
spring.datasource.username=root
spring.datasource.password=password

spring.jpa.hibernate.ddl-auto=update
logging.level.org.hibernate.SQL =DEBUG
spring.jpa.show-sql=true
server.port=8080

logging.level.org.springframework.security=TRACE
```
***Dependency for JWT and spring security*** click here to see [pom.xml](https://github.com/ImCodeHub/SpringSecurityJWT/blob/main/pom.xml)
```pom.xml
<!-- spring security dependency -->
		<dependency>
			<groupId>org.springframework.security</groupId>
			<artifactId>spring-security-test</artifactId>
			<scope>test</scope>
		</dependency>

		<!-- JWT dependency -->
		<dependency>
    		<groupId>io.jsonwebtoken</groupId>
    		<artifactId>jjwt-api</artifactId>
    		<version>0.11.5</version>
		</dependency>
		<dependency>
    		<groupId>io.jsonwebtoken</groupId>
    		<artifactId>jjwt-impl</artifactId>
    		<version>0.11.5</version>
		</dependency>
		<dependency>
    		<groupId>io.jsonwebtoken</groupId>
    		<artifactId>jjwt-jackson</artifactId>
    		<version>0.11.5</version>
		</dependency>
```
### API end points
![image](https://github.com/user-attachments/assets/8e91a6df-4551-4f79-8b4c-7f739fc27f32)

### Controller links
- [Admin Controller](https://github.com/ImCodeHub/SpringSecurityJWT/blob/main/src/main/java/com/Authentication/SpringSecurityJWT/Controller/AdminController.java)
- [Member Controller](https://github.com/ImCodeHub/SpringSecurityJWT/blob/main/src/main/java/com/Authentication/SpringSecurityJWT/Controller/MemberController.java)
- [User Controller](https://github.com/ImCodeHub/SpringSecurityJWT/blob/main/src/main/java/com/Authentication/SpringSecurityJWT/Controller/UserController.java)

### Flow of Spring Security with JWT
1. ***User Request:***
The client sends a login request (typically a POST request) with the user's credentials (username, password) to the /api/v1/auth/login endpoint.
you can see ADMIN, MEMBER has role and passwords are encrypted. 

![image](https://github.com/user-attachments/assets/80176b48-94cb-40c5-b1bc-fcacdb4d3f99)


3. ***Authentication:***
AuthenticationManager processes the login request and verifies the credentials.
AuthenticationProvider is invoked, which authenticates the user's credentials (usually against a database).
If authentication is successful, Spring Security generates an Authentication object.
If authentication fails, an exception is thrown (e.g., BadCredentialsException).

4. ***JWT Generation:***
Upon successful authentication, the JwtService generates a JWT token containing the username and authorities (roles/permissions) claims.
The JWT is signed with a secret key using the HS256 algorithm.
The JWT is sent back to the client as a response in the Authorization header (as a Bearer token).

5. ***Client Stores JWT:***
The client stores the JWT (usually in localStorage or sessionStorage) to send it with subsequent requests.

5. ***Authorized Request:***
For each subsequent request that requires authentication or authorization, the client sends the JWT in the Authorization header (Bearer token).

7. ***JWT Authentication Filter:***
The JwtAuthFilter intercepts each request and extracts the JWT from the Authorization header.
It verifies the token signature using the secret key.
It checks if the token is expired and processes the claims (like roles/permissions).
The filter adds an Authentication object (with user details and roles/permissions) to the SecurityContextHolder.

9. ***Authorization:***
Based on the authorities (roles/permissions) in the JWT, Spring Security handles the authorization.
If the request matches certain secured endpoints:
hasRole("ROLE_ADMIN"): Access is allowed only to users with the ADMIN role.
hasAuthority("admin:create"): Access is allowed to users with the specific permission (admin:create).
If the user has the required role/permission, the request proceeds. Otherwise, an Access Denied error (HTTP 403) is returned.

8. ***Request Handlers:***
If the user is authenticated and authorized, the request reaches the appropriate controller method (secured endpoint).
If authentication or authorization fails at any point, an AuthenticationException or AccessDeniedException is thrown.

10. ***Logout:***
The client sends a logout request (e.g., POST /api/v1/auth/logout).
Spring Security invalidates the session or handles token invalidation (if needed), and the user is logged out.

---
# To contact me:
   - name: Ankit sharma
   - mobile no: 8962780856
   - E-mail id: ankitsharma.as420@gmail.com
   - My [Linked In](https://www.linkedin.com/in/ankit-sharma-a6689b1a5/) Profile.
     
**To see My other projects** [click here](https://github.com/ImCodeHub?tab=repositories)
