package com.invoicetrackingsystem.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.invoicetrackingsystem.model.User;
import com.invoicetrackingsystem.model.UserRole;
import com.invoicetrackingsystem.repo.RoleRepository;
import com.invoicetrackingsystem.repo.UserRepository;
import com.invoicetrackingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class UserDetailsImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) throws Exception {

        User local = this.userRepository.findByUsername(user.getUsername());
        User local2 = this.userRepository.findByEmail(user.getEmail());
        if (local!=null &&local2!=null)
        {
            System.out.println("User is already there !!");
            throw new Exception("User already present !!");
        }
        else
        {
            //user create
            for (UserRole ur : userRoles)
            {

                roleRepository.save(ur.getRole());

            }

            user.getUserRoles().addAll(userRoles);
            local =this.userRepository.save(user);

        }



        return local;
    }

    @Override
    public User getUser(String username) {
        return this.userRepository.findByUsername(username);
    }

    @Override
    public void deleteUser(Long userId) {
        this.userRepository.deleteById(userId);

    }

}
