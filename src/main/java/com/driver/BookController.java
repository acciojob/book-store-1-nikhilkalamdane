package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

//    private List<Book> bookList;
//    private int id;

    @Autowired
    BookService bookService;

//    public List<Book> getBookList() {
//        return bookList;
//    }
//
//    public void setBookList(List<Book> bookList) {
//        this.bookList = bookList;
//    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public BookController(){
//        this.bookList = new ArrayList<Book>();
//        this.id = 1;
//    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        bookService.saveToDB(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        Book book = bookService.getBookFromDB(id);
        if(book == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(book, HttpStatus.FOUND);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") int id){
        Book book = bookService.deleteBookFromDB(id);
        if(book == null){
            return new ResponseEntity<>("Book not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Book " + id + " successfully deleted", HttpStatus.FOUND);
    }


    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooksFromDB(), HttpStatus.OK);
    }


    // delete request /delete-all-books
    // deleteAllBooks()
    public ResponseEntity<String> deleteAllBooks(){
        bookService.deleteAllBooksFromDB();
        return new ResponseEntity<>("All books deleted successfully", HttpStatus.OK);
    }



    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
}
