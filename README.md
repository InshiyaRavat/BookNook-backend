<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>

<body>

  <h1>Booknook-Backend</h1>
    <p>Provides backend services for the Booknook online bookstore application. It manages user authentication, book data, cart functionality, and user profile editing.</p>

  <h2>Features</h2>
    <ul>
        <li>User Authentication: Handles user registration, and login.</li>
        <li>Book Management: Manages book data including title, description, price, and category.</li>
        <li>Cart Functionality: Allows users to add books to their cart, view cart contents, and remove books from the cart.</li>
        <li>User Profile Editing: Provides endpoints for users to edit their usernames.</li>
    </ul>

  <h2>Technologies Used</h2>
    <ul>
        <li>Spring Boot: Framework for building Java-based enterprise applications.</li>
        <li>Spring Data JPA: Part of the Spring Data project that makes it easy to implement JPA-based repositories.</li>
        <li>Hibernate: Object-relational mapping (ORM) library for Java applications.</li>
        <li>PostgreSQL: Relational database management system for storing book and user data.</li>
    </ul>

   <h2>Setup Instructions</h2>
    <ol>
        <li>Clone the repository to your local machine.</li>
        <li>Configure the database settings in the application.properties file.</li>
        <li>Run the application using your IDE.</li>
        <li>Access the API endpoints at <a href="http://localhost:8080">http://localhost:8080</a>.</li>
    </ol>

  <h2>API Documentation</h2>
    <h3>User Endpoints:</h3>
    <ul>
        <li>POST /api/register: Register a new user.</li>
        <li>POST /api/login: Login and obtain an authentication token.</li>
        <li>PATCH /api/user/{userId}: Update the username of the user with the specified ID.</li>
    </ul>
    <h3>Book Endpoints:</h3>
    <ul>
        <li>GET /api/books: Retrieve a list of all books.</li>
        <li>GET /api/books/{bookId}: Retrieve details of a specific book.</li>
        <li>GET /api/books/category/{category}: Retrieve books by category.</li>
    </ul>
    <h3>Cart Endpoints:</h3>
    <ul>
        <li>POST /api/cart/{userId}: Add a book to the user's cart.</li>
        <li>GET /api/cart/{userId}: Retrieve the contents of the user's cart.</li>
        <li>DELETE /api/cart/{userId}/{bookId}: Remove a book from the user's cart.</li>
    </ul>

  <h2>Contributors</h2>
    <p>Inshiya Ravat</p>

  <h2>Contact Details: </h2> insravat@gmail.com

</body>

</html>
