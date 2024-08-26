package org.example.hgproject.user.service;

import org.example.hgproject.user.repository.UserRepository;
import org.example.hgproject.user.dto.CustomUserDetails;
import org.example.hgproject.user.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        UserEntity userData = userRepository.findByUserName(userName);

        if (userData != null)
        {
            return new CustomUserDetails(userData);
        }

        return null;
    }
}
