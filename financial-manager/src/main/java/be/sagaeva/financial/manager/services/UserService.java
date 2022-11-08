package be.sagaeva.financial.manager.services;

import be.sagaeva.financial.manager.data.User;
import be.sagaeva.financial.manager.dto.UserDTO;
import be.sagaeva.financial.manager.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public void save(UserDTO userDTO) {
        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = mapToEntity(userDTO);
        user.setUserId(UUID.randomUUID().toString());
        userRepository.save(user);

    }

    public User getLoggedInUser() {
      Authentication auth =  SecurityContextHolder.getContext().getAuthentication();
      String loggedUserEmail = auth.getName();
      return  userRepository.findByemail(loggedUserEmail).orElseThrow(() ->
              new UsernameNotFoundException("User not foud for the email" + loggedUserEmail));
    }

    private User mapToEntity(UserDTO userDTO) {
       return modelMapper.map(userDTO, User.class);
    }
}
