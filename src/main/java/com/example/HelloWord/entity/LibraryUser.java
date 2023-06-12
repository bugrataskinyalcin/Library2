package com.example.HelloWord.entity;


import jakarta.persistence.*;

@Entity
@Table
public class LibraryUser {

    @Id
    @SequenceGenerator(
            name = "library_user_sequence",
            sequenceName = "library_user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "library_user_sequence"
    )
    private Long id;
    private String first_name;
    private String last_name;

    private String status;

    public LibraryUser(String first_name, String last_name, String status) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
    }

    public LibraryUser() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LibraryUser(Long id, String first_name, String last_name, String status) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.status = status;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "library_user{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
