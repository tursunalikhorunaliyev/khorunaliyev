package com.khorunaliyev.khorunaliyev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterResult {
    private boolean status;
    private String message;
    private String accessToken;

    public RegisterResult(boolean status, String message) {
        this.status = status;
        this.message = message;
    }
}
