package ectimel.controllers;

import ectimel.entities.User;
import ectimel.entities.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TestController {

    private final UserRepository repository;

    public TestController(UserRepository repository) {
        this.repository = repository;
    }
    
    
    @GetMapping()
    public ResponseEntity<List<User>> getUser(){
        List<User> users = new ArrayList<>();
        Iterable<User> iterator = repository.findAll();
        iterator.forEach(users::add);
        return ResponseEntity.ok(users);    
    }
    
    
}
