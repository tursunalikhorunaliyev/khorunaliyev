package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.entity.Roles;
import com.khorunaliyev.khorunaliyev.extra.SecurityVariables;
import com.khorunaliyev.khorunaliyev.repository.RolesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(SecurityVariables.URL_DIRECTION+"/roles")
@AllArgsConstructor
public class RoleController {
    private final RolesRepository rolesRepository;

    @GetMapping()
    public ResponseEntity<Collection<Roles>> getRoles(){
        return ResponseEntity.ok(rolesRepository.findAll());
    }

}
