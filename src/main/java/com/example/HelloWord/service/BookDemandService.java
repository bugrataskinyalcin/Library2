package com.example.HelloWord.service;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.BookDemand;
import com.example.HelloWord.repository.BookDemandRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class BookDemandService {

    private final BookDemandRepository bookDemandRepository;

    @Autowired
    public BookDemandService(BookDemandRepository bookDemandRepository) {
        this.bookDemandRepository = bookDemandRepository;
    }

    public List<BookDemand> getBookDemands(){
        return bookDemandRepository.findAll();
    }

    public void addNewBookDemand(BookDemand bookDemand) {
        bookDemandRepository.save(bookDemand);
    }

    public void deleteBookDemand(Long bookDemandId) {
        boolean exists = bookDemandRepository.existsById(bookDemandId);

        if(!exists)
            throw new IllegalStateException("bookDemand with id" + bookDemandId + "does not exists");

        bookDemandRepository.deleteById(bookDemandId);

    }


    //with that anotation we dont need bookDemand  service .
    @Transactional
    public void updateBookDemand(Long bookDemandId,String title) {

        BookDemand bookDemand = bookDemandRepository.findById(bookDemandId).orElseThrow( () ->new IllegalStateException("bookDemand with id" + bookDemandId + "does not exists"));

        if(title != null && title.length()>0 && !Objects.equals(bookDemand.getTitle(),title)){
            bookDemand.setTitle(title);
        }
    }
}
