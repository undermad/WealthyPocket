package api.modules.user_access.controllers;


import ectimel.cqrs.commands.CommandDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_access.commands.RegisterUser;

import java.util.Enumeration;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController {
    
    private final CommandDispatcher commandDispatcher;

    public RegistrationController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping()
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser command) {
        commandDispatcher.send(command);
        
        return ResponseEntity.ok("User registered");
    }
    
}
