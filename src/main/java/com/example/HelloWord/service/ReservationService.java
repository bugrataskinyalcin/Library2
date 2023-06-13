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

    private final ReservationRepository reservationRepository;
    private final BookRepository bookRepository;
    private final LibraryUserRepository libraryUserRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository) {
        this.reservationRepository = reservationRepository;
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
    }

    public List<Reservation> getReservations(){
        return reservationRepository.findAll();
    }

    public void addNewReservation(Reservation reservation) {

        Optional<Book> book = bookRepository.findById(reservation.getBook().getId());
        Optional<LibraryUser> libraryUser = libraryUserRepository.findById(reservation.getUser().getId());

        if(book.isPresent()&&libraryUser.isPresent()&& Objects.equals(book.get().getStatus(),"shelf")){
            Book bookOld = bookRepository.findById(book.get().getId()).orElseThrow( () ->new IllegalStateException("book with id" + book.get().getId() + "does not exists"));
            bookOld.setStatus("booked");
            reservationRepository.save(reservation);

        }
        else{
            throw new IllegalStateException("error occured,  reservation already exist in the database \n user may not found,\nbook may not found.");
        }
    }

    public void deleteReservation(Long reservationiId) {
        boolean exists = reservationRepository.existsById(reservationiId);

        if(!exists)
            throw new IllegalStateException("reservation with id" + reservationiId + "does not exists");

        reservationRepository.deleteById(reservationiId);

    }


    //with that anotation we dont need libraryUser  service .
    @Transactional
    public void updateReservation(Long reservationId, String status) {

        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow( () ->new IllegalStateException("reservation with id" + reservationId + "does not exists"));

        if(status != null  && !Objects.equals(reservation.getStatus(),status)){
            reservation.setStatus(status);
        }
    }
}
