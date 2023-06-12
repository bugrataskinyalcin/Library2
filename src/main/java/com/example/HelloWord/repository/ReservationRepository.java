package com.example.HelloWord.repository;

import com.example.HelloWord.entity.Loan;
import com.example.HelloWord.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

}
