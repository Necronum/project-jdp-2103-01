package com.kodilla.ecommercee.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class ShopSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.GET, "/v1/order/**", "/v1/cart/**", "/v1/user/**", "/v1/group/**", "/v1/product/**").permitAll()
                .anyRequest().authenticated()
        .and()
                .httpBasic()
        .and()
                .csrf().disable();
    }

}
