package api;

import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

public class TestMainMethod
{

    public static void main(String[] args)
    {

        UUID uuid = UUID.nameUUIDFromBytes("abvc".getBytes());
        

        System.out.println(uuid);
    }
}
