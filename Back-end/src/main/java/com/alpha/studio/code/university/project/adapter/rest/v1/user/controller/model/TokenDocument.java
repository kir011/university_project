package com.alpha.studio.code.university.project.adapter.rest.v1.user.controller.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDocument {
    private String login;
    private String token;
}
