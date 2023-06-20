package com.alpha.studio.code.university.project.adapter.security.jwt;

import com.alpha.studio.code.university.project.application.userAuth.UserServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


public class JwtAuthFilter extends OncePerRequestFilter {

    private JwtService service;
    private UserServiceImpl userService;

    public JwtAuthFilter(JwtService service, UserServiceImpl userService) {
        this.service = service;
        this.userService = userService;
    }

    @Override
    protected void  doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if(authorization!=null && authorization.startsWith("Bearer")){
            String token = authorization.split(" ")[1];
            boolean isValid = service.tokenIsValid(token);
            if(isValid){
                String loginUser = service.getLogin(token);
                UserDetails userDetails = userService.loadUserByUsername(loginUser);
                UsernamePasswordAuthenticationToken user =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                user.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(user);
            }
        }
        filterChain.doFilter(request,response);

    }
}
