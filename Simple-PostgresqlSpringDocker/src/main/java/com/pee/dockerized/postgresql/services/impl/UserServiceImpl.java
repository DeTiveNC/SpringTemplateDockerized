package com.pee.dockerized.postgresql.services.impl;

import java.util.List;

import com.pee.dockerized.postgresql.entity.Role;
import com.pee.dockerized.postgresql.services.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pee.dockerized.postgresql.entity.User;
import com.pee.dockerized.postgresql.repository.UserRepository;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
    @Override
    public User getUserById(Long id){
        Optional<User> getUser = userRepository.findById(id);
        return getUser.orElse(null);
    }
    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }
    @Override
    public User findByEmail(String email){
        Optional<User> getUser = userRepository.findByEmail(email);
        return getUser.orElse(null);
    }
    @Override
    public User findByRole(Role role){
        return userRepository.findByRole(role);
    }

    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
                return userRepository.findByEmail(email)
                        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            }
        };
    }
}