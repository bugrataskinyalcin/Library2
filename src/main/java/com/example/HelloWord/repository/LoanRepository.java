package com.example.HelloWord.repository;

import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan,Long> {

}

