package com.example.JavaProjectPersonalLibrary.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name ="statistics")
public class Statistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private Integer booksRead;

    @Column
    private Double averageReadingTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getBooksRead() {
        return booksRead;
    }

    public void setBooksRead(Integer booksRead) {
        this.booksRead = booksRead;
    }

    public Double getAverageReadingTime() {
        return averageReadingTime;
    }

    public void setAverageReadingTime(Double averageReadingTime) {
        this.averageReadingTime = averageReadingTime;
    }
}
