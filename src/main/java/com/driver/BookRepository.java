package com.driver;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@Component
public class BookRepository {

    HashMap<Integer, Book> bookList;
    int book_id;
    BookRepository(){
        bookList = new HashMap<>();
        book_id = 1;
    }




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

    public List<Book> findAllBooks(){
        List<Book> newBookList = new ArrayList<>();
        for(Book b : bookList.values()){
            newBookList.add(b);
        }

        return newBookList;
    }

    public Book deleteBookByID(int id){
        if(bookList.containsKey(id)){
            return bookList.remove(id);
        }else{
            return null;
        }
    }

    public void deleteAllBooks(){
        bookList.clear();
    }

    public List<Book> findBooksByAuthor(String author){
        List<Book> list = new ArrayList<>();
        for(Book b : bookList.values()){
            if(b.getAuthor().equals(author)){
                list.add(b);
            }
        }

        return list;
    }

    public List<Book> findBooksByGenre(String genre){
        List<Book> list = new ArrayList<>();
        for(Book b : bookList.values()){
            if(b.getGenre().equals(genre)){
                list.add(b);
            }
        }

        return list;


    }
}
