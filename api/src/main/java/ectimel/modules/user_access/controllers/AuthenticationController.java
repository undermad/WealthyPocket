package ectimel.modules.user_access.controllers;

import ectimel.commands.Authenticate;
import ectimel.cqrs.commands.CommandDispatcher;
import ectimel.dto.LoginResponse;
import ectimel.message_broker.MessageBroker;
import ectimel.message_broker.TestEvent;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
