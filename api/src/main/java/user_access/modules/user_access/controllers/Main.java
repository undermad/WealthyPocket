package user_access.modules.user_access.controllers;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class Main {
    
    
     
    
    public static void main(String[] args) {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        
        
        String aa = "domdomdom";

        
        
    }
}

record Test(Test2 abc) {}

record Test2(String abc) {}
