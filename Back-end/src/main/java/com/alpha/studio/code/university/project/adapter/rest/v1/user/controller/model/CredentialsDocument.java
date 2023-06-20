package com.alpha.studio.code.university.project.adapter.rest.v1.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CredentialsDocument {
    private String login;
    private String password;
}
