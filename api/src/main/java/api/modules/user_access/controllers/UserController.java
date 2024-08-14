package api.modules.user_access.controllers;

import api.modules.user_access.dto.ChangePasswordRequest;
import ectimel.cqrs.commands.CommandDispatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_access.commands.ChangePassword;
import user_access.dto.CustomUserDetails;


@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private CommandDispatcher commandDispatcher;

    public UserController(CommandDispatcher commandDispatcher) {
        this.commandDispatcher = commandDispatcher;
    }

    @PostMapping("/change_password")
    public ResponseEntity<String> changePassword(@RequestBody ChangePasswordRequest request,
                                                 @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        
        var command = new ChangePassword(customUserDetails.uuid(), request.oldPassword(), request.newPassword());
        
        commandDispatcher.send(command);
        
        return ResponseEntity.ok("Your password has been changed");
    }


}
