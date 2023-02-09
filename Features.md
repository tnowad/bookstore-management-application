# Bookstore Manageemnt

## Class

```yml
Book:
  name: String,
  category: String[]
  price: int

Series:
  Book: []

Person:
  id: String
  name: String
  dateOfBirth: Date

Customer extend Person:
  Address: Address

Author extend Person:

Worker extend Person:

Employee extend Worker:

Manager extend Worker:
```
