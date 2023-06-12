package com.example.HelloWord.controller;

import com.example.HelloWord.service.LibraryUserService;
import com.example.HelloWord.entity.LibraryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/library_user")
public class LibraryUserController {

    private  final LibraryUserService library_user_service;

    @Autowired
    public LibraryUserController(LibraryUserService library_user_service) {
        this.library_user_service = library_user_service;
    }

    @GetMapping
    public ResponseEntity< List<LibraryUser> > getlibrary_users(){
        return  new ResponseEntity<>(library_user_service.getlibrary_users(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewlibrary_user(@RequestBody LibraryUser library_user){
        library_user_service.addNewlibrary_user(library_user);
        return new ResponseEntity<>("library_user created succesfully",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{library_userId}")
    public ResponseEntity<String> deletelibrary_user(@PathVariable("library_userId") Long library_userId){
        library_user_service.deletelibrary_user(library_userId);
        return new ResponseEntity<>("library_user deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{library_userId}")
    public void updatelibrary_user(
            @PathVariable("library_userId") Long library_userId,
            @RequestParam(required = false) String first_name,
            @RequestParam(required = false) String last_name,
            @RequestParam(required = false) String library_user_status)
    {
        library_user_service.updatelibrary_user(library_userId,first_name,last_name,library_user_status);
    }

}
