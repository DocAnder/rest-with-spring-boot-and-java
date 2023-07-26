package com.example.restwithspringbootandjava.services;

import com.example.restwithspringbootandjava.exceptions.ResourceNotFoundException;
import com.example.restwithspringbootandjava.model.Book;
import com.example.restwithspringbootandjava.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServices {

    @Autowired
    BookRepository bookRepository;


    public List<Book> findAll(){

        var books = bookRepository.findAll();
        return books;

    }

    public Book findById(Long id){
        Book book = bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this ID!")
                );
        return book;
    }


    public Book create(Book book){
        Book bookSave = bookRepository.save(book);

        return bookSave;
    }

    public Book update(Book book){
        Book entity = bookRepository.findById(book.getId()).orElseThrow(
                () -> new ResourceNotFoundException("No records found for this ID!")
        );

        entity.setAutorName(book.getAutorName());
        entity.setSheets(book.getSheets());
        entity.setTitle(book.getTitle());

        var bookUpdate = bookRepository.save(entity);
        return bookUpdate;
    }

    public void delete(Long id){
        Book entity = bookRepository.findById(id)
                .orElseThrow(
                        () -> new ResourceNotFoundException("No records found for this ID!")
                );
        bookRepository.delete(entity);
    }



}
