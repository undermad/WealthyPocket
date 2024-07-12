package ectimel.controllers;

import ectimel.commands.RegisterUser;
import ectimel.cqrs.commands.CommandDispatcher;
import ectimel.entities.User;
import ectimel.repositories.UserRepository;
import ectimel.value_objects.Email;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1")
public class TestController {
    
    
    private final UserRepository repository;
    private final CommandDispatcher commandDispatcher;
    

    public TestController(UserRepository repository, CommandDispatcher commandDispatcher) {
        this.repository = repository;
        this.commandDispatcher = commandDispatcher;
    }

    @GetMapping()
    public ResponseEntity<User> getUser(){
        var users = repository.getAsync(new Email("dtworek94@gmail.com")).join();
        return ResponseEntity.ok(users);    
    }
    
    
    @PostMapping()
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser command) {
        commandDispatcher.send(command);
        return ResponseEntity.ok("User registered");
    }
    
    
}
