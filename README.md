# Bookstore Management Application

This project is a bookstore management application built using Java Swing for the user interface, MySQL for the database, and Docker for containerization.

## Setup Instructions

To set up the application, follow these steps:

1. Make sure you have Docker installed on your system.
2. Clone the project repository.
3. Open a terminal and navigate to the project directory.
4. Run the following command to set up the database using Docker Compose:

```
docker-compose up
```

This command will start a MySQL container and set up the necessary database and tables.
5. Once the database is up and running, you can run the application.
6. In the terminal, run the following command to start the application:

```
java -classpath <path-to-jar-file> com.bookstore.App
```

Replace `<path-to-jar-file>` with the path to the compiled JAR file of the application.
7. The application will launch, and you can now use it to manage your bookstore.

## Additional Notes

- The application utilizes Java Swing for the graphical user interface, providing a user-friendly experience.
- MySQL is used as the database management system to store and retrieve bookstore-related information.
- Docker Compose is employed to simplify the setup process by encapsulating the database in a container.

Feel free to customize the application as per your requirements and preferences. Enjoy managing your bookstore!
