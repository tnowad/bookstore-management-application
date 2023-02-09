# Bookstore Management System

## Table of Contents

- Introduction
- Requirements
- Installation
- Usage
- Conclusion

## Introduction

The Bookstore Management System is a Java Swing-based application that provides an interface for bookstore owners to manage their day-to-day operations. The system is designed to handle inventory, customer and order management, billing and invoicing, and other tasks related to running a bookstore. The system utilizes MongoDB as the database and Docker-Compose for easy deployment and management of the database.

## Requirements

Before you begin, make sure you have the following installed:

- Docker-Compose (version 3.8 or higher)
- Java 8 or higher
- Java Swing library
- MongoDB Java Driver

## Installation

- Clone the repository to your local machine

  ```shell
  git clone https://github.com/tnowad/bookstore-management-application.git
  ```

- Change into the project directory

  ```shell
  cd bookstore-management-application
  ```

- Run Docker-Compose to start the MongoDB container

  ```shell
  docker-compose up -d
  ```

- Build and run the Java project using your preferred method (e.g. using an IDE or the command line)

## Usage

Upon starting the application, you will be prompted to login using a username and password. Once logged in, you will be able to access the various functionalities of the system, such as managing inventory, customers, orders, and invoices.

```yml
default user:
  username: admin
  password: admin
```

## Conclusion

The Bookstore Management System provides an easy-to-use interface for bookstore owners to manage their operations, and the use of MongoDB and Docker-Compose makes deployment and maintenance a breeze. Whether you are running a small or large bookstore, this system can help you streamline your operations and improve your bottom line.
