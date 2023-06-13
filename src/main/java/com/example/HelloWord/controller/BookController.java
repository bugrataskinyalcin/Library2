package com.example.HelloWord.controller;

import com.example.HelloWord.service.BookService;
import com.example.HelloWord.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/book")
public class BookController {

    private  final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity< List<Book> > getBooks(){
        return  new ResponseEntity<>(bookService.getBooks(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewBook(@RequestBody Book book){
        bookService.addNewBook(book);
        return new ResponseEntity<>("book created succesfully",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable("bookId") Long bookId){
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("book deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{bookId}")
    public void updateBook(
            @PathVariable("bookId") Long bookId,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String bookStatus)
    {
        bookService.updateBook(bookId,title,bookStatus);
    }

}
