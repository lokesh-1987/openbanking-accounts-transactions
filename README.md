# Open Banking Accounts Transactions

## Goal

The goal of the technical test is to create a Java web application based on the standard servlet spec.
The application should provide a REST-ful API that retrieve transactions from OpenBank sandbox and
transforms the data as per the required mapping.

# Implementation

## Open Banking Accounts Transactions Application

- This Application basically constructed as the spring boot application and runs on the embedded
  tomcat server on the default port 8080.
- This application provides below three GET end points in the transaction controller class as below,
   a) To get the list of transactions
       - **http://localhost:8080/obp/v1.2.1/banks/{bankId}/accounts/{accountId}/{viewId}/transactions**
   b) To get the list of transactions based on transaction type
       - **http://localhost:8080/obp/v1.2.1/banks/{bankId}/accounts/{accountId}/{viewId}/transactions?
       transactionType=sandbox-payment**
   c) To get total transaction amount based on transaction type
       - **http://localhost:8080/obp/v1.2.1/banks/{bankId}/accounts/{accountId}/{viewId}/transactions/
       totalAmount?transactionType=sandbox-payment**
  **Note** {banId}, {accountId} and {viewId} should be replaced with actual value in URl.
   **As these are three input to all the three GET transactions APIs.**
   **For example** {banId} -> rbs, {accountId} -> savings-kids-john and {viewId} -> public
- These three end points basically call the open bank sandBox in service class which in turn returns
  sand box transaction response.
- Then, this sandbox transaction response is transformed in transaction response as per the given mapping
  in the requirement documents.
- This application basically contains following two security level,
    a) SSL level security
    b) Oath 2.0 Jwt authentication security
- SSL level security can be enabled or disabled by marking the following property as true or false in the
  application.properties file as shown below,
  SSL security Enabled :  **server.ssl.enabled=true**
  SSL security Disabled :  **server.ssl.enabled=false**
- For Oath 2.0 Jwt security, the following end point is created in token controller class,
   **http://localhost:8080/token**
   This end point is basically a POST call, and accepts the following JSON as body,
   for example,
    {
        "userName": "Lokesh",
        "userId": 1234,
        "role": "admin"
    }

   this end point returns the JWT token as below,
   **eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJMb2tlc2giLCJ1c2VySWQiOiIxMjM0Iiwicm9sZSI6ImFkbWluIn0.
   g17gIYpcvJzKHDATOvEEuPAT7oeO7XWRp-NsMxH0tk87R9aHAqKpVyuGyqpteTQDC2vpbsEsnWMDaeKq2q2How**

- This jwt token is added into the header as below for all three GET transactions calls,
    header key : **Authorisation**
    header value : **Token eyJhbGciOiJIUzUxMiJ9.
    eyJzdWIiOiJMb2tlc2giLCJ1c2VySWQiOiIxMjM0Iiwicm9sZSI6ImFkbWluIn0.
    g17gIYpcvJzKHDATOvEEuPAT7oeO7XWRp-NsMxH0tk87R9aHAqKpVyuGyqpteTQDC2vpbsEsnWMDaeKq2q2How**

- Exception handling is also implemented in case of any failure in the application and also
  logging is covered as in when it is required using **Sl4j libraries**.

# Execution

In order to run the application, follow the below steps,
- Open a terminal window
- Change directory to openbanking-accounts-transactions maven project.
  You should be in a directory that contains pom.xml file.
- Run the following command:
    - **mvn spring-boot:run**
- Now the application is up and running on the default port : 8080.
- Make sure Postman is downloaded in your local machine as it has been used to test the transactions API.
- Use POSTMAN to get the jwt token using POST token call and then this token is added into the header
  for all the 3 GET transactions calls as explained above.
- Use the below command to run to run the test cases,
   a) **mvn clean install**
   b) **mvn test**