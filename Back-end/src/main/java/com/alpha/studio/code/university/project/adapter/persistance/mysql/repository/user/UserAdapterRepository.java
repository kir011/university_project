package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user;

import com.alpha.studio.code.university.project.domain.model.user.User;
import com.alpha.studio.code.university.project.domain.port.UserRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.alpha.studio.code.university.project.commons.utils.ClassConverter.*;

@Repository
@RequiredArgsConstructor
public class UserAdapterRepository implements UserRepositoryPort {

    private final UserRepository repository;

    @Override
    public User findUserByLogin(String name) {
        Optional<UserDocument> userDocument = repository.findUserByLogin(name).stream().findFirst();
        return converterUser(userDocument.get());
    }

    @Override
    public User save(User user) {
        return converterUser(repository.save(converterDocumentUser(user)));
    }

}
