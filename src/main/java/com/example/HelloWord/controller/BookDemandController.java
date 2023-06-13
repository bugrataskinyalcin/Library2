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
@RequestMapping(path="/api/v1/bookDemand")
public class BookDemandController {

    private  final BookDemandService bookDemandService;

    @Autowired
    public BookDemandController(BookDemandService bookDemandService) {
        this.bookDemandService = bookDemandService;
    }

    @GetMapping
    public ResponseEntity< List<BookDemand> > getBookDemands(){
        return  new ResponseEntity<>(bookDemandService.getBookDemands(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewBookDemand(@RequestBody BookDemand bookDemand){
        bookDemandService.addNewBookDemand(bookDemand);
        return new ResponseEntity<>("new bookDemand has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{bookDemandId}")
    public ResponseEntity<String> deleteBookDemand(@PathVariable("bookDemandId") Long bookDemandId){
        bookDemandService.deleteBookDemand(bookDemandId);
        return new ResponseEntity<>("bookDemand deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{bookDemandId}")
    public void updateBookDemand(
            @PathVariable("bookDemandId") Long bookDemandId,
            @RequestParam(required = false) String title)

    {
        bookDemandService.updateBookDemand(bookDemandId,title);
    }

}

