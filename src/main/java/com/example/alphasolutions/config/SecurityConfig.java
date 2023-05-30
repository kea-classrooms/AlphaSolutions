package com.example.alphasolutions.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    //Defines our datasource, and injects the database implementation into the variable
    private DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        //defines that we use JDBC authentication, with a BCrypt encoded password
        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .dataSource(dataSource)
                //Defines SQL queries for getting username, password, enabled and role from our database, these are all needed and predefined in Spring Security
                .usersByUsernameQuery("SELECT username, password, enabled FROM employee WHERE username = ?")
                .authoritiesByUsernameQuery("SELECT username, role FROM employee INNER JOIN positions USING (posID) INNER JOIN role USING(roleID) WHERE username = ?");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .authorizeHttpRequests(auth -> auth
                        //Allows everyone to reach root URL
                        .requestMatchers("/", "**.svg", "**.css").permitAll()
                        //All other requests should be authenticated
                        .anyRequest().authenticated()
                )
                //Anyone can access login form
                .formLogin().permitAll()
                .and()
                //anyone can access logout form
                .logout().permitAll()
                .and()
                //build HttpSecurity instance
                .build();
    }
}
