package com.example.HelloWord.service;

import com.example.HelloWord.entity.Book;
import com.example.HelloWord.entity.LibraryUser;
import com.example.HelloWord.repository.LibraryUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LibraryUserService {

    private final LibraryUserRepository library_user_repository;

    @Autowired
    public LibraryUserService(LibraryUserRepository library_user_repository) {
        this.library_user_repository = library_user_repository;
    }

    public List<LibraryUser> getlibrary_users(){
        return library_user_repository.findAll();
    }

    public void addNewlibrary_user(LibraryUser library_user) {
            library_user_repository.save(library_user);
    }

    public void deletelibrary_user(Long library_userId) {
        boolean exists = library_user_repository.existsById(library_userId);

        if(!exists)
            throw new IllegalStateException("library_user with id" + library_userId + "does not exists");

        library_user_repository.deleteById(library_userId);

    }


    //with that anotation we dont need library_user  service .
    @Transactional
    public void updatelibrary_user(Long library_userId, String first_name,String last_name,String status) {

        LibraryUser library_user = library_user_repository.findById(library_userId).orElseThrow( () ->new IllegalStateException("library_user with id" + library_userId + "does not exists"));

        if(first_name != null && first_name.length()>0 && !Objects.equals(library_user.getFirst_name(),first_name)){
            library_user.setFirst_name(first_name);
        }
        if(last_name != null && last_name.length()>0 && !Objects.equals(library_user.getLast_name(),last_name)){
            library_user.setLast_name(last_name);
        }

        if(status != null && status.length()>0 && !Objects.equals(library_user.getStatus(),status)){
            library_user.setStatus(status);
        }
    }
}
