package com.invoicetrackingsystem.config;

import com.invoicetrackingsystem.model.Role;
import com.invoicetrackingsystem.model.User;
import com.invoicetrackingsystem.repo.UserRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class JwtUtil {
    private final UserRepository userRepository;
    private final String CLAIMS_SUBJECT ="sub";
    private final String CLAIMS_ROLE ="role";


    @Value("${auth.secret}")
    private String TOKEN_SECRET;

    public JwtUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public String getUserNameFromToken(String token){
        try {

            Claims claims = getClaims(token);
            return  claims.getSubject();
        }catch (Exception ex){
            return null;
        }
    }
    public String generateToken(UserDetails userDetails){

        Map<String , Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT , userDetails.getUsername());
        claims.put(CLAIMS_ROLE, getRole(userDetails).getRoleName());

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256 , TOKEN_SECRET)
                .compact();

    }
    public Role getRole(UserDetails userDetails){
        User user = userRepository.findByUsername(userDetails.getUsername());
        return user.getUserRoles().get(0).getRole();
    }

//
//    public boolean isTokenValid(String token, UserDetails userDetails) {
//        String username = getUserNameFromToken(token);
//
//        return (username.equals(userDetails.getUsername())&& !isTokenExpired(token));
//    }

//    private boolean isTokenExpired(String token) {
//        Date expiration =getClaims(token).getExpiration();
//        return expiration.before(new Date());
//    }

    private Claims getClaims(String token){
        Claims claims;
        try{
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex){
            claims = null;
        }
        return claims;
    }


}