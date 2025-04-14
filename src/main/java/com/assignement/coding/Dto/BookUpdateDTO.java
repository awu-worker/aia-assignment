package com.assignement.coding.Dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookUpdateDTO {
    private Long id;

    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Title must be alphanumeric")
    private String title;

    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Author must be alphanumeric")
    private String author;

    private boolean published;
}
