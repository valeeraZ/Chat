package com.sylvain.chat.security.service;

import com.sylvain.chat.security.entity.AuthUser;
import com.sylvain.chat.system.entity.User;
import com.sylvain.chat.system.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**Decouple relation of userDetailServiceImpl -> userService
    /*private final UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }*/
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)  {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("username.notfound")
        );
        return new AuthUser(user);
    }
}
