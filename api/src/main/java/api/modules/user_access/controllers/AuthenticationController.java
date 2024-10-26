package api.modules.user_access.controllers;

import api.modules.user_access.dto.AuthenticateRequest;
import jakarta.servlet.http.HttpServletRequest;
import user_access.commands.Authenticate;
import ectimel.cqrs.commands.CommandDispatcher;
import user_access.dto.LoginResponse;
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
    public ResponseEntity<LoginResponse> login(@RequestBody AuthenticateRequest authenticateRequest, HttpServletRequest httpServletRequest) {
        var authenticate = new Authenticate(authenticateRequest.email(), authenticateRequest.password(), httpServletRequest);
        var loginResponse = commandDispatcher.sendWithResult(authenticate);
        return ResponseEntity.ok(loginResponse);
    }
    
}
