package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public Book deleteBookFromDB(int id){
        return bookRepository.deleteBookByID(id);
    }

    public List<Book> getAllBooksFromDB(){
        return bookRepository.findAllBooks();
    }

    public void deleteAllBooksFromDB(){
        bookRepository.deleteAllBooks();
    }

    public List<Book> findBooksByAuthor(String author){
        return bookRepository.findBooksByAuthor(author);
    }

    public List<Book> findBooksByGenre(String genre){
        return bookRepository.findBooksByGenre(genre);
    }
}
