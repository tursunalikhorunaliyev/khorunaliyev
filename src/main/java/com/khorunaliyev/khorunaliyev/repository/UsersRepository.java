package com.khorunaliyev.khorunaliyev.repository;

import com.khorunaliyev.khorunaliyev.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findByUsername(String username);
    boolean existsByUsername(String username);
}