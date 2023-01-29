## Drones

[[_TOC_]]

---

:scroll: **START**


### Running MongoDB on Docker

- Make sure that you have Docker installed on your machine.
- Open a terminal and run the command **docker pull mongo**. This will download the latest version of MongoDB.
- Run the command **docker run -d -p 27017:27017 --name mongodb mongo**. This will start a MongoDB container and map the container's port 27017 to the host's port 27017.
- Verify that the container is running by running the command **docker ps**. You should see the mongodb container in the list of running containers.

---

### Configuring Spring Boot with MongoDB

- In the application properties file (application.properties or application.yml), add the MongoDB configurations.

---

### Running the Application

- Make sure that the MongoDB container is running.
- Run the command **mvn clean install** to build the application.
- Run the command **mvn spring-boot:run** to start the application.
- The application should now be running and connected to the MongoDB container.
- Use **http://localhost:8080/** as the base url.
- You can now interact with the application and test its functionality.

---

#### Testing the Application

- Run the command **mvn test** to run the JUnit tests for the application.
- The tests will run and check the functionality of the application.
- You can also use a tool like Postman to test the REST endpoints of the application.

---

:scroll: **END**
