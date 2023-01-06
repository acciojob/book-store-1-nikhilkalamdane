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

    private List<Book> bookList;
    private int id;



    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()
    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") int id){
        for(int i =0 ; i < bookList.size(); i++){
            if(bookList.get(i).getId() == id)
                return new ResponseEntity<>(bookList.get(i),HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
    @DeleteMapping("delete-book-by-id/{id}")
    public ResponseEntity<String> deleteBookById(@PathVariable("id") int id){
        for(int i =0;i<bookList.size();i++){
            if(bookList.get(i).getId() == id) {
                bookList.remove(i);
                return new ResponseEntity<>("Successfully Deleted!",HttpStatus.OK);
            }
        }
        return new ResponseEntity<>("Book not found",HttpStatus.NOT_FOUND);
    }


    // get request /get-all-books
    // getAllBooks()
    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAllBooks(){
        return new ResponseEntity<>(bookList,HttpStatus.OK);
    }


    // delete request /delete-all-books
    // deleteAllBooks()
    public ResponseEntity<String> deleteAllBooks(){
        bookList = new ArrayList<Book>();
        return new ResponseEntity<>("All books successfully deleted",HttpStatus.OK);
    }



    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author?author=author+name")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam("author") String author){
        List<Book> booksByAuthor = new ArrayList<>();
        for(int i =0;i<bookList.size();i++){
            if(bookList.get(i).getAuthor().equals(author))
                booksByAuthor.add(bookList.get(i));
        }
        return new ResponseEntity<>(booksByAuthor,HttpStatus.OK);
    }


    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre?genre=genre+name")
    public ResponseEntity<List<Book>> getBooksByGenre(@RequestParam("genre") String genre){
        List<Book> booksByGenre = new ArrayList<>();
        for(int i =0;i<bookList.size();i++){
            if(bookList.get(i).getGenre().equals(genre))
                booksByGenre.add(bookList.get(i));
        }
        return new ResponseEntity<>(booksByGenre,HttpStatus.OK);
    }
}
