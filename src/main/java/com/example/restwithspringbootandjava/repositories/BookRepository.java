package com.example.restwithspringbootandjava.repositories;

import com.example.restwithspringbootandjava.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
