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

    private final LoanRepository loan_repository;
    private final BookRepository book_repository;
    private final LibraryUserRepository library_user_repository;

    @Autowired
    public LoanService(LoanRepository loan_repository, BookRepository bookRepository, LibraryUserRepository libraryUserRepository) {
        this.loan_repository = loan_repository;
        book_repository = bookRepository;
        library_user_repository = libraryUserRepository;
    }

    public List<Loan> getLoans(){
        return loan_repository.findAll();
    }

    public void addNewLoan(Loan loan) {


        Optional<Book> book = book_repository.findById(loan.getBook().getId());
        Optional<LibraryUser> libraryUser = library_user_repository.findById(loan.getUser().getId());

        if(book.isPresent()&&libraryUser.isPresent()&& Objects.equals(book.get().getStatus(),"shelf")){
            Book book_old = book_repository.findById(book.get().getId()).orElseThrow( () ->new IllegalStateException("book with id" + book.get().getId() + "does not exists"));
            book_old.setStatus("reading");
            loan_repository.save(loan);

        }
        else{
            throw new IllegalStateException("error occured,  loan already exist in the database \n user may not found,\nbook may not found.");
        }
    }

    public void deleteLoan(Long loaniId) {
        boolean exists = loan_repository.existsById(loaniId);

        if(!exists)
            throw new IllegalStateException("loan with id" + loaniId + "does not exists");

        loan_repository.deleteById(loaniId);

    }


    //with that anotation we dont need library_user  service .
    @Transactional
    public void updateLoan(Long loanId, Book book, LibraryUser user, LocalDate loan_date, LocalDate return_date) {

        Loan loan = loan_repository.findById(loanId).orElseThrow( () ->new IllegalStateException("loan with id" + loanId + "does not exists"));

        if(loan_date != null  && !Objects.equals(loan.getLoan_date(),return_date)){
            loan.setLoan_date(loan_date);
        }
        if(return_date != null  && !Objects.equals(loan.getReturn_date(),return_date)){
            loan.setReturn_date(return_date);
        }

    }
}
