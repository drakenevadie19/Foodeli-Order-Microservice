# idraiske-Order-Microservice
Idraiske Order Microservice for Idraiske Food delivery application
- Responsibility:
  - Save all the information of the restaurant's details, and user details from where to order pickup to where to order has to be served, into the MongoDB database
  - Respond to Endpoint to save order in the database.
- Technologies: 
  - Microservice Architecture
  - RestAPI
  - Java 11
  - MongoDB as Datasource 
  - Spring Boot
  - Lombok
  - Eureka Client
- To Run the Microservice:
  1. Turn on MongoDB Atlas (I use MongoDB Compass to monitor collections of the database): Open a terminal, run these commands in order:
    - brew services start mongodb/brew/mongodb-community
    - mongosh
  => Database will run via port 27017 => localhost:27017
  2. Since this microservice will need to register to Eureka Client Server to manage and connect with other Microservice, I need to turn on Eureka server MS.
    - To start Eureka Client Server, via terminal of Intellij of that Microservice: run main class of this MS.
  3. Finally, run this MS: run main class. 
