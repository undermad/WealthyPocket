package api.modules.user_access.controllers;


import ectimel.cqrs.commands.CommandDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_access.commands.RegisterUser;

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
