package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

@Repository
@Component
public class BookRepository {
    HashMap<Integer, Book> bookList = new HashMap<>();
    int book_id = 0;


    public void save(Book book){
        book_id = bookList.size() + 1;
        book.setId(book_id);
        bookList.put(book_id, book);
        return;
    }

    public Book findBookByID(int id){
        if(bookList.containsKey(id)){
            return bookList.get(id);
        }else{
            return null;
        }

    }
}
