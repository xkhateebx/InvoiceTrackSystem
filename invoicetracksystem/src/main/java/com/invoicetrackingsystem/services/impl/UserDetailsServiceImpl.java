package com.invoicetrackingsystem.services.impl;

import com.invoicetrackingsystem.error.UserFoundException;
import com.invoicetrackingsystem.model.User;
import com.invoicetrackingsystem.model.UserRole;
import com.invoicetrackingsystem.repo.RoleRepository;
import com.invoicetrackingsystem.repo.UserRepository;
import com.invoicetrackingsystem.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if (user==null)
        {
            System.out.println("User not found");
            throw new UsernameNotFoundException("No user found !!");



        }
        return user;
    }
}