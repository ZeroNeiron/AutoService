## Auto Service

This auto service is a simple web application.
- --

### Endpoints:
- GET: /orders/{id}/price?bonus={bonus} - total price with discount
- GET: /repairmans/{id}/orders - get all completed orders by repairman id
- GET: /repairmans/{id}/orders/{id}/salary - get salary by order id and repairman id
- GET: /owners/{id}/orders - get orders by owner id
- POST: /owners - add new owner
- POST: /cars - add new car
- POST: /repairmans - add new repairman
- POST: /orders - add new order
- POST: /orders/{id}/goods - add new goods to order
- POST: /orders/{id}/favors - add new favor to order
- POST: /orders/{id}/favors/{id} - add favor by id to order
- PUT: /orders/{id}}/status?newStatus="STATUS" - change order status
- PUT: /cars/{id} - edit car by id
- PUT: /favors/{id} - edit favor by id
- PUT: /goods/{id} - edit googs by id
- PUT: /orders/{id} - edit order by id
- PUT: /owners/{id} - edit owner by id
- PUT: /repairmans/{id} - edit repairman by id
- --

### If you want to run this project on your computer, you need:
1. Fork and clone this project:
2. Change username and password in application.properties file:
```
    spring.datasource.username="your username"
    spring.datasource.password="your password"
```   
3. Run the application
4. Use Postman for sending your requests during testing this application
- [collection_link](https://www.getpostman.com/collections/0eba2f5771cfcc00e499)
- --

### Used technologies
- Java 11
- SpringBoot
- SpringBoot Data JPA
- PostgreSQL
- Maven checkstyle plugin
