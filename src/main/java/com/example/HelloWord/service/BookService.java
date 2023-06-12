package com.example.HelloWord.service;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getBooks(){
        return bookRepository.findAll();
    }

    public void addNewBook(Book book) {

        bookRepository.save(book);

    }

    public void deleteBook(Long bookId) {
        boolean exists = bookRepository.existsById(bookId);

        if(!exists)
            throw new IllegalStateException("book with id" + bookId + "does not exists");

        bookRepository.deleteById(bookId);

    }


    //with that anotation we dont need book  service .
    @Transactional
    public void updateBook(Long bookId, String name,String status) {

        Book book = bookRepository.findById(bookId).orElseThrow( () ->new IllegalStateException("book with id" + bookId + "does not exists"));

       if(name != null && name.length()>0 && !Objects.equals(book.getTitle(),name)){
           book.setTitle(name);
       }

        if(status != null && status.length()>0 && !Objects.equals(book.getStatus(),status)){
            book.setStatus(status);
        }
    }
}
