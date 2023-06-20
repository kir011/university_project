package com.alpha.studio.code.university.project.application.userAuth;

import com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user.UserDocument;
import com.alpha.studio.code.university.project.domain.exceptions.PasswordInvalidException;
import com.alpha.studio.code.university.project.domain.port.UserRepositoryPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converterDocumentUser;
import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.converterUser;

@Service
public class UserServiceImpl implements UserDetailsService {

    private final BCryptPasswordEncoder encoder;
    private final UserRepositoryPort userRepositoryPort;

    @Autowired
    public UserServiceImpl(@Lazy BCryptPasswordEncoder encoder, @Lazy UserRepositoryPort userRepositoryPort) {
        this.encoder = encoder;
        this.userRepositoryPort = userRepositoryPort;
    }

    @Transactional
    public UserDocument save(UserDocument userDocument){
        return converterDocumentUser(userRepositoryPort.save(converterUser(userDocument)));
    }

    public UserDetails authentication(UserDocument document){
        UserDetails userDetails = loadUserByUsername(document.getLogin());
        boolean verify = encoder.matches(document.getPassword(), userDetails.getPassword());
        if (verify){
            return userDetails;
        }else {
            throw new PasswordInvalidException();
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDocument user = converterDocumentUser(userRepositoryPort.findUserByLogin(username));

        String[] roles = user.isAdmin() ? new String[] {"ADMIN", "USER"} : new String[] {"USER"};

        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
