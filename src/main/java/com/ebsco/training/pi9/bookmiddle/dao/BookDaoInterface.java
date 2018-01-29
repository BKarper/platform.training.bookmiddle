package com.ebsco.training.pi9.bookmiddle.dao;

import com.ebsco.training.pi9.bookmiddle.dto.BookDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

public interface BookDaoInterface {
    @RequestMapping(method = RequestMethod.GET)
    List<BookDto> getBooks();

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    BookDto getBooksById(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST)
    ResponseEntity<BookDto> createBook(@RequestBody(required = true) BookDto book);

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    ResponseEntity<BookDto> deleteBook(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    ResponseEntity<BookDto> updateBook(@PathVariable("id") String id, @RequestBody BookDto book);
}
