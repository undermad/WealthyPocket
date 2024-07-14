package ectimel.modules.user_access.controllers;

import ectimel.commands.Authenticate;
import ectimel.cqrs.commands.CommandDispatcher;
import ectimel.dto.LoginResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    
    private final CommandDispatcher commandDispatcher;


    public AuthenticationController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody Authenticate authenticate) {
        var loginResponse = commandDispatcher.send(authenticate);
        return ResponseEntity.ok(loginResponse);
    } 
    
}
