package com.khorunaliyev.khorunaliyev.repository;

import com.khorunaliyev.khorunaliyev.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {

    Optional<Roles> findByName(String roleName);
}