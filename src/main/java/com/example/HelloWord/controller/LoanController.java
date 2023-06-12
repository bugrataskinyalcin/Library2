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

    private  final LoanService loan_service;

    @Autowired
    public LoanController(LoanService loan_service) {
        this.loan_service = loan_service;
    }

    @GetMapping
    public ResponseEntity< List<Loan> > getLoans(){
        return  new ResponseEntity<>(loan_service.getLoans(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewLoan(@RequestBody Loan loan){
        loan_service.addNewLoan(loan);
        return new ResponseEntity<>("new loan has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{loanId}")
    public ResponseEntity<String> deleteLoan(@PathVariable("loanId") Long loanId){
        loan_service.deleteLoan(loanId);
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
        loan_service.updateLoan(loanId,book,libraryUser,loanDate,returnDate);
    }

}
