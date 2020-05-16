package com.socialbook.socialbook.user;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public User save(User user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

}
