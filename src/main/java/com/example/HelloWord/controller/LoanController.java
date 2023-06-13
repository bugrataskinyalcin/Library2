package com.example.HelloWord.controller;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.service.LoanService;
import com.example.HelloWord.entity.Loan;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/loan")
public class LoanController {

    private  final LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public ResponseEntity< List<Loan> > getLoans(){
        return  new ResponseEntity<>(loanService.getLoans(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewLoan(@RequestBody Loan loan){
        loanService.addNewLoan(loan);
        return new ResponseEntity<>("new loan has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable("loanId") Long loanId){
        loanService.deleteLoan(loanId);
        return new ResponseEntity<>("loan deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{loanId}")
    public void updateLoan(
            @PathVariable("loanId") Long loanId,
            @RequestParam(required = false) Book book,
            @RequestParam(required = false) LibraryUser libraryUser,
            @RequestParam(required = false) LocalDate loanDate,
            @RequestParam(required = false) LocalDate returnDate)

    {
        loanService.updateLoan(loanId,book,libraryUser,loanDate,returnDate);
    }

}
