package com.assignement.coding.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.Pattern;
@Setter
@Getter
public class BookCreateDTO {

    @NotBlank(message = "Title is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must be alphanumeric")
    private String title;

    @NotBlank(message = "Author is required")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Author must be alphanumeric")
    private String author;

    private boolean published;
}
