package api.modules.wallet.controllers;


import api.modules.wallet.dto.TransferFundsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import user_access.dto.CustomUserDetails;
import wallet.cqrs.commands.CommandDispatcher;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/transfer")
public class TransferController
{
    
    public final CommandDispatcher commandDispatcher;

    public TransferController(CommandDispatcher commandDispatcher)
    {
        this.commandDispatcher = commandDispatcher;
    }
    
    @PostMapping
    public ResponseEntity<String> transferFunds(@RequestBody TransferFundsDto transferFundsDto,
                                              @AuthenticationPrincipal CustomUserDetails customUserDetails)
    {
        var transferFunds = TransferFundsDto.mapToCommand(customUserDetails.userId(), transferFundsDto);
        commandDispatcher.send(transferFunds);
        
        return ResponseEntity.ok("Transfer successful");
    }

    public static void main(String[] args)
    {

    }
    
}
