# Architecture Diagram

## Application Architecture Diagram

User Interface: Java Swing components

Business Logic: The logic that implements the business rules and processes. Communicates with the DAO to retrieve and store data.

Entity Classes: represent the entities in the system, contain properties and methods that represent the data and behavior of the entities.

Exception Handling: Responsible for handling exceptions that may occur in the system. It catches exceptions, logs them, and displays appropriate error messages to the user.

Data Access Object (DAO): Communicating with MongoDB to retrieve and store data. Uses the Java MongoDB Driver to connect to MongoDB and perform operations on the data.
