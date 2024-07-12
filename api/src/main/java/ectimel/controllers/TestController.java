package ectimel.controllers;

import ectimel.entities.User;
import ectimel.repositories.UserRepository;
import ectimel.value_objects.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class TestController {
    
    
    private final UserRepository repository;

    public TestController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(){
        var users = repository.getAsync(new Email("dtworek94@gmail.com")).join();
        return ResponseEntity.ok(users);    
    }
    
    
}
