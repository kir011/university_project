package com.alpha.studio.code.university.project.domain.model.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User {
    private Integer id;
    @NotEmpty
    private String login;
    @NotEmpty
    private String password;
    @NotEmpty
    private boolean admin;
}
