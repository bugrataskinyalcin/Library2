package com.example.HelloWord.controller;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.service.BookDemandService;
import com.example.HelloWord.entity.BookDemand;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/book_demand")
public class BookDemandController {

    private  final BookDemandService book_demand_service;

    @Autowired
    public BookDemandController(BookDemandService book_demand_service) {
        this.book_demand_service = book_demand_service;
    }

    @GetMapping
    public ResponseEntity< List<BookDemand> > getBookDemands(){
        return  new ResponseEntity<>(book_demand_service.getBookDemands(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewBookDemand(@RequestBody BookDemand book_demand){
        book_demand_service.addNewBookDemand(book_demand);
        return new ResponseEntity<>("new book_demand has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{bookDemandId}")
    public ResponseEntity<String> deleteBookDemand(@PathVariable("bookDemandId") Long bookDemandId){
        book_demand_service.deleteBookDemand(bookDemandId);
        return new ResponseEntity<>("book_demand deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{bookDemandId}")
    public void updateBookDemand(
            @PathVariable("bookDemandId") Long bookDemandId,
            @RequestParam(required = false) String title)

    {
        book_demand_service.updateBookDemand(bookDemandId,title);
    }

}

