package com.pee.dockerized.postgresql.services;

import com.pee.dockerized.postgresql.entity.Role;
import com.pee.dockerized.postgresql.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    User getUserById(Long id);

    User saveUser(User user);

    User findByEmail(String email);

    User findByRole(Role role);

    UserDetailsService userDetailsService();
}
