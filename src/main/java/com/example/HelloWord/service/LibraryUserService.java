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

    private final LibraryUserRepository libraryUserRepository;

    @Autowired
    public LibraryUserService(LibraryUserRepository libraryUserRepository) {
        this.libraryUserRepository = libraryUserRepository;
    }

    public List<LibraryUser> getLibraryUsers(){
        return libraryUserRepository.findAll();
    }

    public void addNewLibraryUser(LibraryUser libraryUser) {
            libraryUserRepository.save(libraryUser);
    }

    public void deleteLibraryUser(Long libraryUserId) {
        boolean exists = libraryUserRepository.existsById(libraryUserId);

        if(!exists)
            throw new IllegalStateException("libraryUser with id" + libraryUserId + "does not exists");

        libraryUserRepository.deleteById(libraryUserId);

    }


    //with that anotation we dont need libraryUser  service .
    @Transactional
    public void updateLibraryUser(Long libraryUserId, String firstName,String lastName,String status) {

        LibraryUser libraryUser = libraryUserRepository.findById(libraryUserId).orElseThrow( () ->new IllegalStateException("libraryUser with id" + libraryUserId + "does not exists"));

        if(firstName != null && firstName.length()>0 && !Objects.equals(libraryUser.getFirstName(),firstName)){
            libraryUser.setFirstName(firstName);
        }
        if(lastName != null && lastName.length()>0 && !Objects.equals(libraryUser.getLastName(),lastName)){
            libraryUser.setLastName(lastName);
        }

        if(status != null && status.length()>0 && !Objects.equals(libraryUser.getStatus(),status)){
            libraryUser.setStatus(status);
        }
    }
}
