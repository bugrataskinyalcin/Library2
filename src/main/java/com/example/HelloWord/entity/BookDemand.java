package com.example.HelloWord.entity;


import jakarta.persistence.*;

@Entity
@Table
public class BookDemand {

    @Id
    @SequenceGenerator(
            name = "book_demand_sequence",
            sequenceName = "book_demand_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_demand_sequence"
    )
    private Long id ;

    @ManyToOne
    private LibraryUser user;

    private String title;


    public BookDemand() {
    }

    public BookDemand(Long id, LibraryUser user, String title) {
        this.id = id;
        this.user = user;
        this.title = title;
    }

    public BookDemand(LibraryUser user, String title) {
        this.user = user;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryUser getUser() {
        return user;
    }

    public void setUser(LibraryUser user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
