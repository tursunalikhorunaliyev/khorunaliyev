package com.khorunaliyev.khorunaliyev.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @OneToOne
    @JoinColumn(name = "province_id", referencedColumnName = "id")
    private Province province;

    @OneToOne
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    private District district;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false, length = 100)
    private String chiefFirstName;

    @Column(nullable = false, length = 100)
    private String chiefLastName;

    @Column(nullable = false, length = 100)
    private String chiefThirdName;

    @Column(nullable = false, length = 9, unique = true)
    private String STIR;

    @Column(unique = true)
    private String email;

    @Column(nullable = false, unique = true, length = 13)
    private String phone;

    @Column(unique = true)
    private String bankAccount;

}