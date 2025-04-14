package com.assignement.coding;

import com.assignement.coding.Dto.BookCreateDTO;
import com.assignement.coding.Dto.BookUpdateDTO;
import com.assignement.coding.controller.BookController;
import com.assignement.coding.entity.Book;
import com.assignement.coding.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
        objectMapper = new ObjectMapper();
    }
    @Test
    void createBook() throws Exception {
        BookCreateDTO bookDTO = new BookCreateDTO();
        bookDTO.setTitle("Test book");
        bookDTO.setAuthor("Tester");
        bookDTO.setPublished(true);

        Book book = new Book();
        book.setId(1L);
        book.setTitle("Test book");
        book.setAuthor("Tester");
        book.setPublished(true);

        when(bookService.createBook(any(BookCreateDTO.class))).thenReturn(book);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test book"));
    }

    @Test
    void createBook_InvalidTitle() throws Exception {
        var bookDTO = new BookCreateDTO();
        bookDTO.setTitle("@InvalidBook");
        bookDTO.setAuthor("Tester");
        bookDTO.setPublished(true);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void createBook_InvalidAuthor() throws Exception {
        var bookDTO = new BookCreateDTO();
        bookDTO.setTitle("Book");
        bookDTO.setAuthor("@InvalidTester");
        bookDTO.setPublished(true);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getBooks() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test book");
        book1.setAuthor("Tester");
        book1.setPublished(true);

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Test book 2");
        book2.setAuthor("Tester");
        book2.setPublished(false);

        when(bookService.getBooks(Optional.empty(), Optional.empty()))
                .thenReturn(Arrays.asList(book1, book2));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Test book"))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Test book 2"));
    }

    @Test
    void getBook() throws Exception {
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Test book");
        book1.setAuthor("Tester");
        book1.setPublished(true);

        when(bookService.getBook(1L))
                .thenReturn(book1);

        mockMvc.perform(get("/books/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Test book"));
    }

    @Test
    void updateBook() throws Exception {
        BookUpdateDTO bookDTO = new BookUpdateDTO();
        bookDTO.setTitle("Updated Book");
        bookDTO.setAuthor("Updated Author");
        bookDTO.setPublished(false);

        Book updatedBook = new Book();
        updatedBook.setId(1L);
        updatedBook.setTitle("Updated Book");
        updatedBook.setAuthor("Updated Author");
        updatedBook.setPublished(false);

        when(bookService.updateBook(eq(1L), any(BookUpdateDTO.class))).thenReturn(updatedBook);

        mockMvc.perform(put("/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Updated Book"))
                .andExpect(jsonPath("$.author").value("Updated Author"))
                .andExpect(jsonPath("$.published").value(false));
    }

    @Test
    void deleteBook() throws Exception {
        mockMvc.perform(delete("/books/1"))
                .andExpect(status().isNoContent());
    }
}
