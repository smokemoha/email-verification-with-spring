 Email Verification System
![Email Verification System](https://img.shields.io/badge/Status-Active-brightgreen)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-14.x-blue)

A Spring Boot project showcasing user registration, email verification via a tokenized verification link, and secure user login.

 Features
- User Registration: Secure sign-up process with validation
- Email Verification: Token-based email verification system
- Authentication: Secure login with Spring Security
- Modern UI: Clean, responsive interface built with Bootstrap
- Database Storage: Persistent data storage with PostgreSQL
 Technologies
- Backend: Spring Boot, Spring Security, Spring Data JPA
- Frontend: Thymeleaf, Bootstrap 5, Font Awesome
- Database: PostgreSQL
- Email Service: JavaMailSender with SMTP
- Testing: JUnit 5, Spring Boot Test
  
 Getting Started
Prerequisites
- JDK 21 or higher
- Maven 4.0.
- PostgreSQL 17+
- SMTP server access (Gmail account recommended)

Installation
1. Clone the repository

```bash
git clone https://github.com/smokemoha/emailverification.git
cd emailverification
```
2. Configure application.properties
Update the following properties in src/main/resources/application.properties :
# Database Configuration
```
spring.datasource.url=jdbc:postgresql://localhost:5432/emailverification
spring.datasource.username=your_username
spring.datasource.password=your_password

 Email Configuration
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_app_password
Note : For Gmail, you need to use an App Password if you have 2FA enabled. Generate one at Google Account Security .
```
3. Build the application
 ```
   mvn clean install
   ```
4. Run the application
```
mvn spring-boot:run
```
The application will be available at http://localhost:8080
 Usage
1. Registration
   
   - Navigate to /register
   - Fill in your details and submit
   - Check your email for the verification link
2. Email Verification
   
   - Click the verification link in your email
   - You'll be redirected to a confirmation page
3. Login
   
   - Navigate to /login
   - Enter your credentials
   - After successful login, you'll be redirected to the home page
   
4. Testing
Run the automated tests with:
```
mvn test
```
  Security Features
 
- Password encryption using BCrypt
- CSRF protection
- Secure email verification tokens
- Session management
- Protected endpoints with Spring Security

  
  API Documentation
  
The application provides the following endpoints:

- GET /register - Registration page
- POST /register - Process registration
- GET /confirm - Email verification
- GET /login - Login page
- GET /home - Home page (authenticated users only)
  
  Contributing
 
Contributions are welcome! Please feel free to submit a Pull Request.

1. Fork the repository
2. Create your feature branch ( git checkout -b feature/amazing-feature )
3. Commit your changes ( git commit -m 'Add some amazing feature' )
4. Push to the branch ( git push origin feature/amazing-feature )
5. Open a Pull Request
