package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public void saveToDB(Book book){
        bookRepository.save(book);
    }

    public Book getBookFromDB(int id){
        return bookRepository.findBookByID(id);
    }
}
