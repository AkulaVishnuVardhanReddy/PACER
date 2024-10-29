package com.Backend.PACER.controllers.configurations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .authorizeHttpRequests(authorize->authorize
                        .requestMatchers("/judge/**").hasRole("JUDGE")
                        .requestMatchers("/lawyer/**").hasRole("LAWYER")
                        .requestMatchers("/registrar/**").hasRole("REGISTRAR")
                        .requestMatchers("/auth/**").permitAll()
                        .anyRequest().authenticated())
                .formLogin(form->form
                        .loginProcessingUrl("/login")
                        .permitAll());

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http)throws Exception{
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}