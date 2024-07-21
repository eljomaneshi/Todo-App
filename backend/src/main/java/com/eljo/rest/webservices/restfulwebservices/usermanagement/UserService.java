package com.eljo.rest.webservices.restfulwebservices.usermanagement;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public boolean changePassword(String username, String newPassword) {
        Optional<User> optionalUser = userRepository.findById(username);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
