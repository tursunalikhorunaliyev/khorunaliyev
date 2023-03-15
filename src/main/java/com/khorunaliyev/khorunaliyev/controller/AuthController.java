package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.extra.SecurityVariables;
import com.khorunaliyev.khorunaliyev.model.LoginResult;
import com.khorunaliyev.khorunaliyev.model.RegisterResult;
import com.khorunaliyev.khorunaliyev.model.Student;
import com.khorunaliyev.khorunaliyev.service.AuthService;
import com.khorunaliyev.khorunaliyev.service.EmailSenderService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;

@AllArgsConstructor
@RestController
@RequestMapping(SecurityVariables.URL_DIRECTION +"/test")
public class AuthController {

    private final AuthService authService;
    private final EmailSenderService emailSenderService;

    @PostMapping("login")
    public ResponseEntity<LoginResult> login(@RequestParam("username") String username, @RequestParam("password") String password){
        return authService.login(username, password);
    }
    @PostMapping("register")
    public ResponseEntity<RegisterResult> register(@RequestParam( "username") String username, @RequestParam( "password") String password, @RequestParam("role_id") Long role_id){
        return authService.register(username, password, role_id);
    }

    @GetMapping("student")
    public ResponseEntity<String> getStudent(){
        return ResponseEntity.ok("<h1>Hello</h1>");
    }
    @GetMapping("send/email")
    public ResponseEntity<String> sendSmsToEmail(@RequestParam String email, @RequestParam String password){
        emailSenderService.setJavaMailSender(email, password);
        return ResponseEntity.ok("Sended");
    }
}
