package com.khorunaliyev.khorunaliyev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class LoginResult {
    private boolean status;

    private String message;

    private String tokenType = null;

    private String token = null;
}
