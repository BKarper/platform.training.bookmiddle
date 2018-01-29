package com.ebsco.training.pi9.bookmiddle.dao;

import com.ebsco.training.pi9.bookmiddle.dto.BookDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ConditionalOnProperty(value="book.useStubs", havingValue="false", matchIfMissing = true)
@Repository
public class BookDao implements BookDaoInterface {

    private List<BookDto> books = new ArrayList<BookDto>();
    private Integer idCounter = 1;

    @Override
    @RequestMapping(method = RequestMethod.GET)
    public List<BookDto> getBooks() {
        return books;
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public BookDto getBooksById(@PathVariable("id") String id) {
        for (BookDto book : books) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BookDto> createBook(@RequestBody(required = true) BookDto book) {
        book.setId(String.valueOf(idCounter++));
        books.add(book);
        return new ResponseEntity<BookDto>(book, HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity<BookDto> deleteBook(@PathVariable("id") String id) {
        for (Iterator<BookDto> iter = books.iterator(); iter.hasNext();) {
            BookDto book = iter.next();
            if (book.getId().equals(id)) {
                iter.remove();
                return ResponseEntity.status(HttpStatus.OK).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity<BookDto> updateBook(@PathVariable("id") String id, @RequestBody BookDto book) {
        for (int i = 0; i < books.size(); i++) {
            BookDto existing = books.get(i);
            if (existing.getId().equals(id)) {
                book.setId(existing.getId());
                books.add(Integer.valueOf(id), book);
                books.remove(existing);
                return ResponseEntity.status(HttpStatus.OK).body(book);
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostConstruct
    public void setup() {
        books.add(new BookDto(String.valueOf(idCounter++), "Foo", "Bar", "Fiction"));
        books.add(new BookDto(String.valueOf(idCounter++), "Fooa", "Bara", "Fiction"));
        books.add(new BookDto(String.valueOf(idCounter++), "Foob", "Barb", "Fiction"));
    }
}
