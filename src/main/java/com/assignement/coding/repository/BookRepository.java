package com.assignement.coding.repository;

import com.assignement.coding.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository  extends JpaRepository<Book, Long> {
    List<Book> findByAuthorContainingIgnoreCaseAndPublished(String author, boolean published);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByPublished(boolean published);
}
