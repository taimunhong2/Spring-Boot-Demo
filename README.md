# Spring Boot Demo
 
### Build with Maven Dependency

### How to run the application
- Run with Java 17
- Tomcat server (Run it in intellij)

### DB configuration
- Added a remote db in this project
- url: sql6.freesqldatabase.com
- port: 3306
- username: sql6527919
- password: VvemzGElH9

### Server url and port
- localhost:8080

### Exisiting Rest API and function
- (Post) http://localhost:8080/admin/login     | Login to get JWT Token (Authentication)
<br/>Request body
{
    "username": "admin",
    "password": "1234"
}

- (Get) http://localhost:8080/admin/batch-job | Batch Job (No need Authentication)
- (Get) http://localhost:8080/transaction/all | Get All transaction from the DB (Required Authentication/login)

- (Post) http://localhost:8080/transaction     | Get All transaction based on the request body (includes pagination, customer id, description and account number)
<br/>Request body
{
    "customerID": "222",
    "description": "FUND TRANSFER",
    "accountNumber": "8872838283",
    "pageNo": 0, 
    "pageSize": 10
}



