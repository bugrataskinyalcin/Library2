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

    private final BookDemandRepository book_demand_repository;

    @Autowired
    public BookDemandService(BookDemandRepository book_demand_repository) {
        this.book_demand_repository = book_demand_repository;
    }

    public List<BookDemand> getBookDemands(){
        return book_demand_repository.findAll();
    }

    public void addNewBookDemand(BookDemand book_demand) {
        book_demand_repository.save(book_demand);
    }

    public void deleteBookDemand(Long book_demandId) {
        boolean exists = book_demand_repository.existsById(book_demandId);

        if(!exists)
            throw new IllegalStateException("book_demand with id" + book_demandId + "does not exists");

        book_demand_repository.deleteById(book_demandId);

    }


    //with that anotation we dont need book_demand  service .
    @Transactional
    public void updateBookDemand(Long book_demandId,String title) {

        BookDemand book_demand = book_demand_repository.findById(book_demandId).orElseThrow( () ->new IllegalStateException("book_demand with id" + book_demandId + "does not exists"));

        if(title != null && title.length()>0 && !Objects.equals(book_demand.getTitle(),title)){
            book_demand.setTitle(title);
        }
    }
}
