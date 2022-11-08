package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServices implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
       User user =  userRepository.findByemail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found for the email" + email));
      return new  org.springframework.security.core.userdetails.User(
               user.getEmail(),
               user.getPassword(),
               new ArrayList<>());
    }
}
