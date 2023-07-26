package com.example.restwithspringbootandjava.controller;


import com.example.restwithspringbootandjava.model.Book;
import com.example.restwithspringbootandjava.services.BookServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequestMapping("/api/book/v1")
public class BookController {

    @Autowired
    private BookServices bookServices;

    @GetMapping(
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}
    )
    public List<Book> findAll(){
        return bookServices.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book create(@RequestBody Book book){
        return bookServices.create(book);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book update(@RequestBody Book book){
        return bookServices.update(book);
    }

    @GetMapping(value = "/{id}",
    produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public Book findById(@PathVariable(value = "id") Long id){
        return bookServices.findById(id);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id){
       bookServices.delete(id);
       return ResponseEntity.noContent().build();
    }




}
