package com.khorunaliyev.khorunaliyev.service;

import com.khorunaliyev.khorunaliyev.entity.Roles;
import com.khorunaliyev.khorunaliyev.entity.Users;
import com.khorunaliyev.khorunaliyev.model.LoginResult;
import com.khorunaliyev.khorunaliyev.model.RegisterResult;
import com.khorunaliyev.khorunaliyev.repository.RolesRepository;
import com.khorunaliyev.khorunaliyev.repository.UsersRepository;
import com.khorunaliyev.khorunaliyev.security.CustomeUserDetailsService;
import com.khorunaliyev.khorunaliyev.security.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;


@AllArgsConstructor
@Service
public class AuthService {

    private final UsersRepository usersRepository;
    private  final RolesRepository rolesRepository;

    private final CustomeUserDetailsService customeUserDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private final TokenGenerator tokenGenerator;

    public ResponseEntity<RegisterResult> register(String username, String password) {
        boolean isOk = validateUserData(username, password);
        if (isOk && usersRepository.existsByUsername(username)) {

            RegisterResult result = new RegisterResult(false, "Bu foydalanuvchi allaqachon ro'yxatdan o'tgan");
            return ResponseEntity.ok(result);

        }

        if (isOk && !usersRepository.existsByUsername(username)) {

            final Users users = new Users();
            users.setUsername(username.trim());
            users.setPassword(passwordEncoder.encode(password.trim()));
            final Roles roles = rolesRepository.findByName("ROLE_USER").get();
            users.setRoles(Collections.singleton(roles));
            Users registeredUser = usersRepository.save(users);
            if (registeredUser != null) {

                    final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username.trim(), password.trim()));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    final String token = tokenGenerator.generateToken(authentication);


                RegisterResult registerResult = new RegisterResult(true, "Foydalanuvchi muvaffaqiyyatli ro'yxatdan o'tdi", token);
                return ResponseEntity.ok(registerResult);

            }
            else{
                return null;
            }
        }
        else{
            return null;
        }

    }
    public ResponseEntity<LoginResult> login(String username, String password){
        if(validateUserData(username, password)){
            final Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username.trim(), password.trim()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            final String token = tokenGenerator.generateToken(authentication);
            final LoginResult result = new LoginResult(true, "Foydalanuvchi tizimga kirdi", "Bearer", token);
            return ResponseEntity.ok(result);
        }
        else{
            return null;
        }

    }

    private boolean validateUserData(String username, String password){
        return !username.trim().isEmpty() || password.trim().length()>=8;
    }


}
