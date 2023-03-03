package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.model.LoginResult;
import com.khorunaliyev.khorunaliyev.model.RegisterResult;
import com.khorunaliyev.khorunaliyev.model.Student;
import com.khorunaliyev.khorunaliyev.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/test")
public class AuthController {

    private final AuthService authService;

    @PostMapping("login")
    public ResponseEntity<LoginResult> login(@RequestParam("username") String username, @RequestParam("password") String password){
        return authService.login(username, password);
    }
    @PostMapping("register")
    public ResponseEntity<RegisterResult> register( @RequestParam( "username") String username, @RequestParam( "password") String password){
        return authService.register(username, password);
    }

    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student("Mo'minjon","Yusupov", (short) 12);
        return ResponseEntity.ok(student);
    }

}
