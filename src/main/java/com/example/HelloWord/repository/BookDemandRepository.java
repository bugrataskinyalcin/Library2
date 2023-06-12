package com.example.HelloWord.repository;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.BookDemand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookDemandRepository extends JpaRepository<BookDemand,Long> {
}
