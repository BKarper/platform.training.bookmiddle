package com.ebsco.training.pi9.bookmiddle.services;

import com.ebsco.training.pi9.bookmiddle.dao.BookDaoInterface;
import com.ebsco.training.pi9.bookmiddle.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    BookDaoInterface bookDao;

    public List<BookDto> getBooks() {
        return bookDao.getBooks();
    }

    public BookDto getBooksById(String id) {
        return bookDao.getBooksById(id);
    }

    public ResponseEntity<BookDto> createBook(BookDto book) {
        return bookDao.createBook(book);
    }

    public ResponseEntity<BookDto> deleteBook(String id) {
        return bookDao.deleteBook(id);
    }

    public ResponseEntity<BookDto> updateBook(String id, BookDto book) {
        return bookDao.updateBook(id, book);
    }
}
