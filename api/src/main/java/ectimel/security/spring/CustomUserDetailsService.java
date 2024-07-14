package ectimel.security.spring;

import ectimel.cqrs.queries.QueryDispatcher;
import ectimel.queries.LoadUserByUsername;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final QueryDispatcher queryDispatcher;

    public CustomUserDetailsService(QueryDispatcher queryDispatcher) {
        this.queryDispatcher = queryDispatcher;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var query = new LoadUserByUsername(email);
        var result = queryDispatcher.query(query).join();
        var authorities = result.roles()
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
        
        return new User(result.email(), result.password(), authorities);
    }
}
