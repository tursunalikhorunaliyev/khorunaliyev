package com.khorunaliyev.khorunaliyev.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class AnyResult {
    private boolean status;
    private String message;
}
