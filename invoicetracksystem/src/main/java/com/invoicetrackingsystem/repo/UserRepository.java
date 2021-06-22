package com.invoicetrackingsystem.repo;

import com.invoicetrackingsystem.model.Invoice;
import com.invoicetrackingsystem.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    //username , it will return the user of given username

     User findByUsername(String username);
     User findByEmail(String email);
    List<User> findAll();




}
