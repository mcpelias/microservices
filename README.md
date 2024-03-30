this microservice project consist of two spring boot project.the BookService which handles the data of the books and responsible from adding,updating and deleting of books in the H2 database.
the second project is the OrderService which communicates to the BookService project through searching the book title if it is present in the database, if found the order will proceed and if not it will display
an error to the user informing that the book is not found in the database. this project also has swagger for documentation of the list of API used in these projects.and can also check if the API's are properly working
as what their specific purpose in each of it.this project is also using H2 database for storing the data . 
