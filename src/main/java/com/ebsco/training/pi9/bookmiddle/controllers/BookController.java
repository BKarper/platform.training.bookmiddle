package com.ebsco.training.pi9.bookmiddle.controllers;

import com.ebsco.training.pi9.bookmiddle.dto.BookDto;
import com.ebsco.training.pi9.bookmiddle.dao.BookDao;
import com.ebsco.training.pi9.bookmiddle.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return service.getBooks();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookDto getBooksById(@PathVariable("id") String id) {
        return service.getBooksById(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BookDto> createBook(@RequestBody(required = true) BookDto book) {
        return service.createBook(book);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") String id) {
        return service.deleteBook(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") String id, @RequestBody BookDto book) {
        return service.updateBook(id, book);
    }
}
