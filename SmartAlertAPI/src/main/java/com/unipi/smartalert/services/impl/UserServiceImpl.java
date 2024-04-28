package com.unipi.smartalert.services.impl;

import com.unipi.smartalert.exceptions.ResourceNotFoundException;
import com.unipi.smartalert.models.Role;
import com.unipi.smartalert.models.User;
import com.unipi.smartalert.repositories.UserRepository;
import com.unipi.smartalert.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("Invalid email or password.");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), user.getPassword(), mapRolesToAuthorities(List.of(user.getRole()))
        );
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getTitle())).collect(Collectors.toList());
    }

    @Override
    public User findByEmail(String email) {
        User user = userRepository.findByEmail(email);

        if (user == null) {
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }

        return user;
    }

    @Override
    public void save(User user) {
        user.setId(0L);
        userRepository.save(user);
    }

}