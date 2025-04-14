package com.assignement.coding.service;

import com.assignement.coding.Dto.BookCreateDTO;
import com.assignement.coding.Dto.BookUpdateDTO;
import com.assignement.coding.entity.Book;
import com.assignement.coding.exception.BookNotFoundException;
import com.assignement.coding.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book createBook(BookCreateDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        book.setPublished(bookDTO.isPublished());
        return bookRepository.save(book);
    }

    public Book getBook(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public List<Book> getBooks(Optional<String> author, Optional<Boolean> published) {
        if (author.isPresent() && published.isPresent()) {
            return bookRepository.findByAuthorContainingIgnoreCaseAndPublished(author.get(), published.get());
        } else if (author.isPresent()) {
            return bookRepository.findByAuthorContainingIgnoreCase(author.get());
        } else if (published.isPresent()) {
            return bookRepository.findByPublished(published.get());
        }
        return bookRepository.findAll();
    }

    public Book updateBook(Long id, BookUpdateDTO bookDTO) {
        Book book = getBook(id);
        if (bookDTO.getTitle() != null && !bookDTO.getTitle().trim().isEmpty()) {
            book.setTitle(bookDTO.getTitle());
        }
        if (bookDTO.getAuthor() != null && !bookDTO.getAuthor().trim().isEmpty()) {
            book.setAuthor(bookDTO.getAuthor());
        }
        if (bookDTO.isPublished() != book.isPublished()) {
            book.setPublished(bookDTO.isPublished());
        }
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFoundException("Book not found");
        }
        bookRepository.deleteById(id);
    }
}
