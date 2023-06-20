package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserDocument, Integer> {

    List<UserDocument> findUserByLogin(String login);

}
