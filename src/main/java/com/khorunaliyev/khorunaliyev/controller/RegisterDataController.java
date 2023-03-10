package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.entity.District;
import com.khorunaliyev.khorunaliyev.entity.Position;
import com.khorunaliyev.khorunaliyev.entity.Province;
import com.khorunaliyev.khorunaliyev.extra.Viloyatlar;
import com.khorunaliyev.khorunaliyev.repository.DistrictRepository;
import com.khorunaliyev.khorunaliyev.repository.PositionRepository;
import com.khorunaliyev.khorunaliyev.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/api/register-info")
public class RegisterDataController {

    final ProvinceRepository provinceRepository;
    final DistrictRepository districtRepository;
    final PositionRepository positionRepository;


    @GetMapping("positions")
    public ResponseEntity<List<Position>> getAllPosition(){
        return ResponseEntity.ok(positionRepository.findAll());
    }

    @GetMapping("provinces")
    public ResponseEntity<List<Province>> getAllProvinces(){
       return ResponseEntity.ok(provinceRepository.findAll());
    }

    @GetMapping("districts")
    public  ResponseEntity<List<District>> getAllDistricts(){
        return ResponseEntity.ok(districtRepository.findAll());
    }

    @GetMapping("provinces-test")
    public ResponseEntity<List<Viloyatlar>> getAllProvincesTest(){
        LinkedList<Viloyatlar> viloyatlars = new LinkedList<>();
        viloyatlars.add(new Viloyatlar(1 , "Farg'ona"));
        viloyatlars.add(new Viloyatlar(2, "Andijon"));
        viloyatlars.add(new Viloyatlar(3, "Namangan"));
        viloyatlars.add(new Viloyatlar(4, "Samarqand"));
        viloyatlars.add(new Viloyatlar(5, "Buxoro"));
        viloyatlars.add(new Viloyatlar(6, "Jizzax"));
        viloyatlars.add(new Viloyatlar(7, "Xorazm"));
        viloyatlars.add(new Viloyatlar(8, "Navoiy"));
        viloyatlars.add(new Viloyatlar(9, "Qashqadaryo"));
        viloyatlars.add(new Viloyatlar(10, "Sirdaryo"));
        viloyatlars.add(new Viloyatlar(11, "Surxondaryo"));
        viloyatlars.add(new Viloyatlar(12, "Toshkent"));
        viloyatlars.add(new Viloyatlar(13, "Qoraqalpog'iston"));

        return ResponseEntity.ok(viloyatlars);
    }



    @PostMapping("positions")
    public void setPosition(@RequestParam("name") String name){
        Position position = new Position();
        position.setName(name);
        positionRepository.save(position);
    }

    @PostMapping("provinces")
    public void setProvince(@RequestParam("name") String name){
       Province province = new Province();
       province.setName(name);
       provinceRepository.save(province);
    }

    @PostMapping("districts")
    public void setDistrict(@RequestParam("name") String name, @RequestParam("province_id") Long id){
       District district = new District();
       district.setName(name);
       final Province province = provinceRepository.findById(id).get();
       district.setProvinces(Collections.singleton(province));
       districtRepository.save(district);
    }




}
