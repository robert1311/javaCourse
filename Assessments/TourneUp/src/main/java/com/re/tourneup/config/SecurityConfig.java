/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.re.tourneup.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 *
 * @author rober
 */@Configuration
 @EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
     @Autowired
     public void configureGlobalInMemory(AuthenticationManagerBuilder auth) 
             throws Exception {
             auth.inMemoryAuthentication().withUser("user")
                     .password("{noop}password").roles("USER")
                     .and()
                     .withUser("admin").password(("{noop}password"))
                     .roles("ADMIN", "USER");
     }
     
     @Override
     protected void configure(HttpSecurity http) throws Exception {
         http
                 .authorizeRequests()
                    .antMatchers("/admin").hasRole("ADMIN")
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/css/**", "/js/**", "/fonts/**").permitAll()
                    .anyRequest().hasRole("USER")//all other pages available for 
                        //all users with "User" role
                 .and()
                 .formLogin()
                    .loginPage("/login")
                    .failureUrl("/login?login_error=1")
                    .permitAll()
                 .and()
                 .logout()
                    .logoutSuccessUrl("/")
                    .permitAll();
     }
}
