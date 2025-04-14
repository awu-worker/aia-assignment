package com.assignement.coding.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must be alphanumeric")
    private String title;

    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Author must be alphanumeric")
    private String author;

    private boolean published;
}
