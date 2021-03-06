package com.invoicetrackingsystem.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/user/create").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/api/v1/invoices").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/api/v1/**").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/invoices").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/invoices/**").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/api/v1/**").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/uploadfile").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/uploadfile/form1").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/all").allowedMethods("GET", "POST", "PUT");
                registry.addMapping("/currentuser").allowedMethods("GET", "POST", "PUT");



            }
        };
    }

}