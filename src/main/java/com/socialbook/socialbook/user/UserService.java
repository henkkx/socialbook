package com.socialbook.socialbook.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    public User save(User user){
        return userRepository.save(user);
    }

}
