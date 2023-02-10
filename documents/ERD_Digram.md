# ERD diagram

## Entities

- Book
  + Title
  + ISBN
  + Author
  + Publisher
  + Publication date

- genre
  + id
  + name genre

- Customer (khách không có thông tin là khách vãng lai)
  + căn cước
  + Name
  + Address
  + email
  + Phone Number
  + Purchase History

- Order
  + Customer Information
  + Order date
  + Order total
  + Shipping Information
  + Book details

- Invoice
  + invoice id
  + Order
  + Invoice date
  + Total Amount Due
  + Payment status

- Payment
  + Order
  + Payment date
  + Payment method
  + Payment amount
  +

- Employee
  + Position
  + Salary
  + Role
  + Contact Information
  + Work Schedule

- warehouse
  - Book
  - Quantity in stock
  - Restock date
  - Book prices
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
  - Promotion type
  - Eligibility criteria
- Gift Card
  - Card number
  - Value
  - Expiration date
  - Redemption history
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

| Name      | Name                     | Relationships |
| --------- | ------------------------ | ------------- |
| Book      | Author                   | One-to-Many   |
| Book      | Publisher                | One-to-Many   |
| Book      | Book Category            | Many-to-Many  |
| Book      | Book Review              | One-to-Many   |
| Book      | Book Reservation         | One-to-Many   |
| Book      | Book Rental              | One-to-Many   |
| Book      | Promotions               | Many-to-Many  |
| Book      | Gift Card                | Many-to-Many  |
| Customer  | Order                    | One-to-Many   |
| Customer  | Payment                  | One-to-Many   |
| Customer  | Invoice                  | One-to-Many   |
| Customer  | Book Reservation         | One-to-Many   |
| Customer  | Customer Loyalty Program | One-to-One    |
| Customer  | Promotions               | Many-to-Many  |
| Customer  | Gift Card                | Many-to-Many  |
| Customer  | Newsletter               | One-to-Many   |
| Order     | Employee                 | One-to-Many   |
| Order     | Payment                  | One-to-Many   |
| Order     | Invoice                  | One-to-One    |
| Order     | Book Reservation         | One-to-Many   |
| Order     | Book Rental              | One-to-Many   |
| Employee  | Employee Task            | Many-to-Many  |
| Employee  | Newsletter               | One-to-Many   |
| Inventory | Book                     | One-to-Many   |
| Invoice   | Payment                  | One-to-One    |
| Payment   | Gift Card                | Many-to-One   |
| Payment   | Promotions               | Many-to-Many  |
