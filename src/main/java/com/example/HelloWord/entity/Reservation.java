package com.example.HelloWord.entity;


import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table
public class Reservation {

    @Id
    @SequenceGenerator(
            name = "reservation_sequence",
            sequenceName = "reservation_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "reservation_sequence"
    )
    private Long id ;
    private LocalDate reservation_date;

    @OneToOne
    private LibraryUser user;

    @OneToOne
    private Book book;

    private String status;

    public Reservation() {
    }

    public Reservation(Long id, LocalDate reservation_date, LibraryUser user, Book book, String status) {
        this.id = id;
        this.reservation_date = reservation_date;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public Reservation(LocalDate reservation_date, LibraryUser user, Book book, String status) {
        this.reservation_date = reservation_date;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getReservation_date() {
        return reservation_date;
    }

    public void setReservation_date(LocalDate reservation_date) {
        this.reservation_date = reservation_date;
    }

    public LibraryUser getUser() {
        return user;
    }

    public void setUser(LibraryUser user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
