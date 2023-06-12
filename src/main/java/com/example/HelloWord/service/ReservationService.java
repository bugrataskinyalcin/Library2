package com.example.HelloWord.service;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.entity.Reservation;
import com.example.HelloWord.repository.BookRepository;
import com.example.HelloWord.repository.LibraryUserRepository;
import com.example.HelloWord.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservation_repository;
    private final BookRepository book_repository;
    private final LibraryUserRepository library_user_repository;

    @Autowired
    public ReservationService(ReservationRepository reservation_repository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository) {
        this.reservation_repository = reservation_repository;
        book_repository = bookRepository;
        library_user_repository = libraryUserRepository;
    }

    public List<Reservation> getReservations(){
        return reservation_repository.findAll();
    }

    public void addNewReservation(Reservation reservation) {

        Optional<Book> book = book_repository.findById(reservation.getBook().getId());
        Optional<LibraryUser> libraryUser = library_user_repository.findById(reservation.getUser().getId());

        if(book.isPresent()&&libraryUser.isPresent()&& Objects.equals(book.get().getStatus(),"shelf")){
            Book book_old = book_repository.findById(book.get().getId()).orElseThrow( () ->new IllegalStateException("book with id" + book.get().getId() + "does not exists"));
            book_old.setStatus("booked");
            reservation_repository.save(reservation);

        }
        else{
            throw new IllegalStateException("error occured,  reservation already exist in the database \n user may not found,\nbook may not found.");
        }
    }

    public void deleteReservation(Long reservationiId) {
        boolean exists = reservation_repository.existsById(reservationiId);

        if(!exists)
            throw new IllegalStateException("reservation with id" + reservationiId + "does not exists");

        reservation_repository.deleteById(reservationiId);

    }


    //with that anotation we dont need library_user  service .
    @Transactional
    public void updateReservation(Long reservationId, String status) {

        Reservation reservation = reservation_repository.findById(reservationId).orElseThrow( () ->new IllegalStateException("reservation with id" + reservationId + "does not exists"));

        if(status != null  && !Objects.equals(reservation.getStatus(),status)){
            reservation.setStatus(status);
        }
    }
}
