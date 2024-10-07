package api.modules.user_access.controllers;


import ectimel.cqrs.commands.CommandDispatcher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import user_access.commands.RegisterUser;

@RestController
@RequestMapping("/api/v1/register")
public class RegistrationController
{

    private final CommandDispatcher commandDispatcher;

    public RegistrationController(CommandDispatcher commandDispatcher)
    {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping()
    public ResponseEntity<String> registerUser(@RequestBody RegisterUser command)
    {
        commandDispatcher.send(command);
        return new ResponseEntity<>("User registered.", HttpStatus.CREATED);
    }

}
