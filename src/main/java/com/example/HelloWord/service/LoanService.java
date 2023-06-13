package com.example.HelloWord.service;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.entity.Loan;
import com.example.HelloWord.repository.BookRepository;
import com.example.HelloWord.repository.LibraryUserRepository;
import com.example.HelloWord.repository.LoanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final LibraryUserRepository libraryUserRepository;

    @Autowired
    public LoanService(LoanRepository loanRepository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository) {
        this.loanRepository = loanRepository;
        this.bookRepository = bookRepository;
        this.libraryUserRepository = libraryUserRepository;
    }

    public List<Loan> getLoans(){
        return loanRepository.findAll();
    }

    public void addNewLoan(Loan loan) {


        Optional<Book> book = bookRepository.findById(loan.getBook().getId());
        Optional<LibraryUser> libraryUser = libraryUserRepository.findById(loan.getUser().getId());

        if(book.isPresent()&&libraryUser.isPresent()&& Objects.equals(book.get().getStatus(),"shelf")){
            Book bookOld = bookRepository.findById(book.get().getId()).orElseThrow( () ->new IllegalStateException("book with id" + book.get().getId() + "does not exists"));
            bookOld.setStatus("reading");
            loanRepository.save(loan);

        }
        else{
            throw new IllegalStateException("error occured,  loan already exist in the database \n user may not found,\nbook may not found.");
        }
    }

    public void deleteLoan(Long loaniId) {
        boolean exists = loanRepository.existsById(loaniId);

        if(!exists)
            throw new IllegalStateException("loan with id" + loaniId + "does not exists");

        loanRepository.deleteById(loaniId);

    }


    //with that anotation we dont need libraryUser  service .
    @Transactional
    public void updateLoan(Long loanId, Book book, LibraryUser user, LocalDate loanDate, LocalDate returnDate) {

        Loan loan = loanRepository.findById(loanId).orElseThrow( () ->new IllegalStateException("loan with id" + loanId + "does not exists"));

        if(loanDate != null  && !Objects.equals(loan.getLoanDate(),returnDate)){
            loan.setLoanDate(loanDate);
        }
        if(returnDate != null  && !Objects.equals(loan.getReturnDate(),returnDate)){
            loan.setReturnDate(returnDate);
        }

    }
}
