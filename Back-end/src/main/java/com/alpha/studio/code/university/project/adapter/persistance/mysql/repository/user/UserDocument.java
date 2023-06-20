package com.alpha.studio.code.university.project.adapter.persistance.mysql.repository.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserDocument {
    @Id
    @GeneratedValue
    private Integer id;
    private String login;
    private String password;
    private boolean admin;
}
