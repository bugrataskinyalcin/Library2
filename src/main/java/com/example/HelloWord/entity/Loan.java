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
    private LocalDate loan_date;
    private LocalDate return_date;

    @OneToOne
    private Book book;

    @OneToOne
    private LibraryUser user;

    public Loan() {
    }

    public Loan(Long id, LocalDate loan_date, LocalDate return_date, Book book, LibraryUser user) {
        this.id = id;
        this.loan_date = loan_date;
        this.return_date = return_date;
        this.book = book;
        this.user = user;
    }

    public Loan(LocalDate loan_date, LocalDate return_date, Book book, LibraryUser user) {
        this.loan_date = loan_date;
        this.return_date = return_date;
        this.book = book;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLoan_date() {
        return loan_date;
    }

    public void setLoan_date(LocalDate loan_date) {
        this.loan_date = loan_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
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
