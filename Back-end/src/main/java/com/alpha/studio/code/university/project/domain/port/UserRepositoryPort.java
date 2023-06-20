package com.alpha.studio.code.university.project.domain.port;

import com.alpha.studio.code.university.project.domain.model.user.User;

import java.util.List;

public interface UserRepositoryPort {
    User findUserByLogin(String name);
    User save(User user);
}
