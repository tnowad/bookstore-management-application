USE bookstore ;

SELECT * FROM publishers;

SELECT * FROM authors;

SELECT
    books.book_name,
    books.book_description,
    books.book_price,
    books.book_image,
    books.book_isbn,
    books.book_pages,
    books.book_year,
    publishers.publisher_name,
    authors.author_name
FROM books
    INNER JOIN publishers ON books.publisher_id = publishers.publisher_id
    INNER JOIN authors ON books.author_id = authors.author_id;

SELECT * FROM genre;

SELECT
    books.book_name,
    genre.genre_name
FROM books
    INNER JOIN book_genre ON books.book_id = book_genre.book_id
    INNER JOIN genre ON book_genre.genre_id = genre.genre_id;