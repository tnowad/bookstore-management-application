# Folder Structure

```file
Bookstore-Management-application:
  - bin:
  - documents:
  - lib:
  - src:
    - main:
      - java:
        - com:
          - bookstore:
              - models:
                # UserModel.java
                # EX: It looks like place to handle data from database
              - views:
                # UserView.java
                # EX: Show UI
              - controllers:
                # UserController.java
                # EX: It uses to control views, update model
              - utils:
                # HashPassword.java
                # EX: import file excel to data
      - resource:
        - mongodb:
          mongodb.properties
    - test:
      - java:
        - com:
          - bookstore:
  - README.md:
```
