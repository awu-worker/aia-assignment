### How to Run
1. Create a database named `books` in MySQL.
2. Update `application.yml` with your MySQL url, username and password.
3. Run `mvn flyway:migrate` to create table
4. Run application `mvn clean install` and `mvn spring-boot:run`.
5. Run test `mvn test`.
---

### ScreenShots
#### 1. Add Book
![img.png](imgs/add_book.png)
#### 2. Book List
![img_1.png](imgs/book_list.png)
#### 3. Book List Filter By "published"
![img_2.png](imgs/book_list_filter_published.png)
#### 4. Delete Book
![img_3.png](imgs/del_book.png)
#### 5. Update Book
![img_4.png](imgs/update_book.png)
#### 6. Add Book, Error: title is required
![img.png](imgs/add_book_error.png)
#### 7. Delete Book, Error: book not found
![img_5.png](imgs/del_book_error.png)
