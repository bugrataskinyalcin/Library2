package com.example.HelloWord.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Loan {

    @Id
    @SequenceGenerator(
            name = "loan_sequence",
            sequenceName = "loan_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "loan_sequence"
    )
    private Long id ;
    private LocalDate loanDate;
    private LocalDate returnDate;

    @OneToOne
    private Book book;

    @OneToOne
    private LibraryUser user;

    public Loan() {
    }

    public Loan(Long id, LocalDate loanDate, LocalDate returnDate, Book book, LibraryUser user) {
        this.id = id;
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Loan(LocalDate loanDate, LocalDate returnDate, Book book, LibraryUser user) {
        this.loanDate = loanDate;
        this.returnDate = returnDate;
        this.book = book;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(LocalDate loanDate) {
        this.loanDate = loanDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public LibraryUser getUser() {
        return user;
    }

    public void setUser(LibraryUser user) {
        this.user = user;
    }
}
