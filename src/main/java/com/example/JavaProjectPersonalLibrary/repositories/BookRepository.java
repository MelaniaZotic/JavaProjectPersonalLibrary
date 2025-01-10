package com.example.JavaProjectPersonalLibrary.repositories;

import com.example.JavaProjectPersonalLibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(propagation = Propagation.MANDATORY)
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    @Query("SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findBookByTitle(String title);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    Book save(Book entity);
}
