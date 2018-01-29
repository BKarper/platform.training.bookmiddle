package com.ebsco.training.pi9.bookmiddle.dao;

import com.ebsco.training.pi9.bookmiddle.dto.BookDto;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@ConfigurationProperties(prefix = "book.stubSettings")
@ConditionalOnProperty(value="book.useStubs", havingValue="true")
@Repository
public class BookDaoStub implements BookDaoInterface {

    private List<BookDto> books = new ArrayList<BookDto>();
    private Integer idCounter = 1;

    public BookDaoStub() {
        books.add(new BookDto(String.valueOf(idCounter++), "Foo", "Bar", "Fiction"));
        books.add(new BookDto(String.valueOf(idCounter++), "Fooa", "Bara", "Fiction"));
        books.add(new BookDto(String.valueOf(idCounter++), "Foob", "Barb", "Fiction"));
    }

    @Override
    public List<BookDto> getBooks() {
        return books;
    }

    @Override
    public BookDto getBooksById(String id) {
        return books.get(1);
    }

    @Override
    public ResponseEntity<BookDto> createBook(BookDto book) {
        return new ResponseEntity<BookDto>(book, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<BookDto> deleteBook(String id) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Override
    public ResponseEntity<BookDto> updateBook(String id, BookDto book) {
        return ResponseEntity.status(HttpStatus.OK).body(book);
    }
}
