package com.example.JavaProjectPersonalLibrary.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name ="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is mandatory")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "Author is mandatory")
    @Size(min = 2, max = 50, message = "Author name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String author;

    @NotNull(message = "Progress must be specified")
    @Column(nullable = false)
    private Double progress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}