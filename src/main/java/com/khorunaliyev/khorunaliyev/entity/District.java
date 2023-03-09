package com.khorunaliyev.khorunaliyev.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "district")
public class District {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(unique = true, nullable = false)
    private String name;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "prov_dist", joinColumns = @JoinColumn(name = "province_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "district_id", referencedColumnName = "id"))
    private Set<Province> provinces;
}