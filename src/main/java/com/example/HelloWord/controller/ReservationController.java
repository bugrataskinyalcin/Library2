package com.example.HelloWord.controller;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.service.ReservationService;
import com.example.HelloWord.entity.Reservation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/reservation")
public class ReservationController {

    private  final ReservationService reservation_service;

    @Autowired
    public ReservationController(ReservationService reservation_service) {
        this.reservation_service = reservation_service;
    }

    @GetMapping
    public ResponseEntity< List<Reservation> > getReservations(){
        return  new ResponseEntity<>(reservation_service.getReservations(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewReservation(@RequestBody Reservation reservation){
        reservation_service.addNewReservation(reservation);
        return new ResponseEntity<>("new reservation has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{reservationId}")
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservation_service.deleteReservation(reservationId);
        return new ResponseEntity<>("reservation deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{reservationId}")
    public void updateReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestParam(required = false) String status)
    {
        reservation_service.updateReservation(reservationId,status);
    }

}
