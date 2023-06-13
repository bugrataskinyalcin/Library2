package com.example.HelloWord.controller;

import com.example.HelloWord.service.LibraryUserService;
import com.example.HelloWord.entity.LibraryUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/v1/libraryUser")
public class LibraryUserController {

    private  final LibraryUserService libraryUserService;

    @Autowired
    public LibraryUserController(LibraryUserService libraryUserService) {
        this.libraryUserService = libraryUserService;
    }

    @GetMapping
    public ResponseEntity< List<LibraryUser> > getLibraryUsers(){
        return  new ResponseEntity<>(libraryUserService.getLibraryUsers(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> registerNewLibraryUser(@RequestBody LibraryUser libraryUser){
        libraryUserService.addNewLibraryUser(libraryUser);
        return new ResponseEntity<>("libraryUser created succesfully",HttpStatus.OK);
    }

    //this is how we obtain strings from path
    @DeleteMapping(path = "{libraryUserId}")
    public ResponseEntity<String> deleteLibraryUser(@PathVariable("libraryUserId") Long libraryUserId){
        libraryUserService.deleteLibraryUser(libraryUserId);
        return new ResponseEntity<>("libraryUser deleted succesfully",HttpStatus.OK);
    }
    @PutMapping(path = "{libraryUserId}")
    public void updateLibraryUser(
            @PathVariable("libraryUserId") Long libraryUserId,
            @RequestParam(required = false) String firstName,
            @RequestParam(required = false) String lastName,
            @RequestParam(required = false) String libraryUserStatus)
    {
        libraryUserService.updateLibraryUser(libraryUserId,firstName,lastName,libraryUserStatus);
    }

}
