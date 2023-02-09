# ERD diagram

## Entities

- Book
  - Title
  - ISBN
  - Author
  - Publisher
  - Publication date
  -
- Customer
  - Name
  - Address
  - email
  - Phone Number
  -
- Order
  - Customer
  - Order date
  - Order total
  - Shipping information
  -
- Employee
  - Position
  - Salary
  -
- Inventory
  - Book
  - Quantity in stock
  - Restock date
  -
- Invoice
  - Order
  - Invoice date
  - Total Amount Due
  -
- Payment
  - Order
  - Payment date
  - Payment method
  -
- Book Reservation
  - Customer
  - Book
  - Reservation date
- Promotions
  - Offer description
  - Start date
  - End date
  - Discount amount
  -
- Gift Card
  - Card number
  - Value
  - Expiration date
- Author
  - Name
  - Nationality
- Publisher
  - Name
  - Location
- Book Category: Fiction, non-Fiction, Children's books...
- Book Review
  - Customer
  - Book
  - Rating
  - Review
- Employee Task
  - Employee
  - Task description
  - Due date
- Newsletter
  - Title
  - Content
  - Send date
- Book Rental
  - Customer
  - Book
  - Rental date
  - Due date

## Relationship

| Name     | Name    | Relationships |
| -------- | ------- | ------------- |
| Book     | Order   | One-to-Many   |
| Customer | Order   | One-to-Many   |
| Order    | Payment | One-to-One    |
| Employee | Order   | One-to-Many   |
