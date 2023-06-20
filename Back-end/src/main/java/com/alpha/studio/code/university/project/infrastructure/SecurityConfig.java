package com.alpha.studio.code.university.project.infrastructure;

import com.alpha.studio.code.university.project.adapter.security.jwt.JwtAuthFilter;
import com.alpha.studio.code.university.project.adapter.security.jwt.JwtService;
import com.alpha.studio.code.university.project.application.userAuth.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private UserServiceImpl userServiceImpl;
    private JwtService service;

    @Autowired
    public SecurityConfig(UserServiceImpl userServiceImpl, JwtService service) {
        this.userServiceImpl = userServiceImpl;
        this.service = service;
    }

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(service, userServiceImpl);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.POST,"/user").permitAll()
                .requestMatchers(HttpMethod.POST,"/user/**").permitAll()
                .requestMatchers(HttpMethod.POST,"/product/**").permitAll()//.hasAnyRole("ADMIN", "USER")
                .requestMatchers(HttpMethod.PUT,"/product/**").permitAll()
                .requestMatchers(HttpMethod.DELETE,"/product/**").permitAll()
                .requestMatchers(HttpMethod.GET,"/product/**").permitAll()//.hasAnyRole("ADMIN", "USER")
//                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class)
                .build();

    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userServiceImpl)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

}
