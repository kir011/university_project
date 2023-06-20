package com.alpha.studio.code.university.project.adapter.rest.v1.user.controller;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user.UserDocument;
import com.alpha.studio.code.university.project.adapter.rest.v1.user.controller.model.CredentialsDocument;
import com.alpha.studio.code.university.project.adapter.rest.v1.user.controller.model.TokenDocument;
import com.alpha.studio.code.university.project.adapter.security.jwt.JwtService;
import com.alpha.studio.code.university.project.application.userAuth.UserServiceImpl;
import com.alpha.studio.code.university.project.domain.exceptions.PasswordInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder encoder;
    private  final JwtService jwtService;

    @Autowired
    public UserController(@Lazy UserServiceImpl userService,@Lazy BCryptPasswordEncoder encoder, @Lazy JwtService jwtService) {
        this.userService = userService;
        this.encoder = encoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/createdUser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDocument save(@RequestBody UserDocument document){
        document.setPassword(encoder.encode(document.getPassword()));
        return userService.save(document);
    }

    @PostMapping("/auth")
    public TokenDocument authentication(@RequestBody CredentialsDocument credentialsDocument){
        try{
            UserDocument user = UserDocument.builder().login(credentialsDocument.getLogin()).password(credentialsDocument.getPassword()).build();
            UserDetails userAuthenticated = userService.authentication(user);
            return new TokenDocument(user.getLogin(), jwtService.tokenGenerate(userAuthenticated));
        }catch (UsernameNotFoundException | PasswordInvalidException ex) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, ex.getMessage());
        }
    }
}
