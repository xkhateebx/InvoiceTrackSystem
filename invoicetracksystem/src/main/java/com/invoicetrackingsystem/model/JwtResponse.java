package com.invoicetrackingsystem.model;

import java.util.List;

public class JwtResponse {

    String token;



    public JwtResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
