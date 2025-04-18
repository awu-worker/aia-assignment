### How to Run
1. Create a database named `books` in MySQL.
2. Update `application.yaml` with the MySQL url, username and password.
3. Run `mvn clean install`.
4. Run `mvn spring-boot:run`.

### How to test
1. Run test `mvn test`.
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
