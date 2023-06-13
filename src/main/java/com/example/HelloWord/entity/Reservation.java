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
    private LocalDate reservationDate;

    @OneToOne
    private LibraryUser user;

    @OneToOne
    private Book book;

    private String status;

    public Reservation() {
    }

    public Reservation(Long id, LocalDate reservationDate, LibraryUser user, Book book, String status) {
        this.id = id;
        this.reservationDate = reservationDate;
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public Reservation(LocalDate reservationDate, LibraryUser user, Book book, String status) {
        this.reservationDate = reservationDate;
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
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
