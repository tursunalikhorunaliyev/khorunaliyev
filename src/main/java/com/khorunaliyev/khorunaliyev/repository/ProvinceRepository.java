package com.khorunaliyev.khorunaliyev.repository;

import com.khorunaliyev.khorunaliyev.entity.Position;
import com.khorunaliyev.khorunaliyev.entity.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {

}
