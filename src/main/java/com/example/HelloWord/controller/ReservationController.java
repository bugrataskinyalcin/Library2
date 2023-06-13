package com.example.HelloWord.controller;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.service.ReservationService;
import com.example.HelloWord.entity.Reservation;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(path="/api/v1/reservation")
public class ReservationController {

    private  final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    @Cacheable(value = "reservations")
    public ResponseEntity< List<Reservation> > getReservations(){
        return  new ResponseEntity<>(reservationService.getReservations(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createNewReservation(@RequestBody Reservation reservation){
        reservationService.addNewReservation(reservation);
        return new ResponseEntity<>("new reservation has created",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{reservationId}")
    @CacheEvict(value = "reservations",allEntries = true)
    public ResponseEntity<String> deleteReservation(@PathVariable("reservationId") Long reservationId){
        reservationService.deleteReservation(reservationId);
        return new ResponseEntity<>("reservation deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{reservationId}")
    @CachePut(value = "reservations",key = "#reservationId")
    public void updateReservation(
            @PathVariable("reservationId") Long reservationId,
            @RequestParam(required = false) String status)
    {
        reservationService.updateReservation(reservationId,status);
    }

}
