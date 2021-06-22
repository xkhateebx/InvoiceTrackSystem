package com.invoicetrackingsystem.controller;

import com.invoicetrackingsystem.config.JwtUtil;
import com.invoicetrackingsystem.model.*;
import com.invoicetrackingsystem.repo.RoleRepository;
import com.invoicetrackingsystem.repo.UserRepository;
import com.invoicetrackingsystem.services.impl.UserDetailsImpl;
import com.invoicetrackingsystem.services.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AuthenticateController {


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtil jwtUtil;


    //generate token
    @PostMapping("/generate-token")
    public JwtResponse Login(@RequestBody User user) {
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        org.springframework.security.core.userdetails.UserDetails userDetails1 = userDetailsService.loadUserByUsername(user.getUsername());

        String token = jwtUtil.generateToken(userDetails1);
        return new JwtResponse(token);

    }

    @GetMapping("/current-user")
    public User getCurrentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }


    private void authenticate(String username,String password) throws Exception {

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));

        }catch (DisabledException e)
        {

            throw new Exception("USER DISABLED" + e.getMessage());


        }catch (BadCredentialsException e)
        {
            throw new Exception("Invalid Credentials " + e.getMessage());
        }




    }

    @GetMapping("/all")

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }




    @GetMapping("/roles")

    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping("/currentuser")
    public User currentUser(Principal principal){
        return ((User) this.userDetailsService.loadUserByUsername(principal.getName()));
    }





}
