package com.khorunaliyev.khorunaliyev.security;

import com.khorunaliyev.khorunaliyev.entity.Users;
import com.khorunaliyev.khorunaliyev.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class CustomeUserDetailsService implements UserDetailsService {
    private final UsersRepository usersRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Users users = usersRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("Username not foound"));
        final Set<GrantedAuthority> authorities = users.getRoles().stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toSet());
        return new User(users.getUsername(), users.getPassword(), authorities);
    }
}
