package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.entity.District;
import com.khorunaliyev.khorunaliyev.entity.Position;
import com.khorunaliyev.khorunaliyev.entity.Province;
import com.khorunaliyev.khorunaliyev.repository.DistrictRepository;
import com.khorunaliyev.khorunaliyev.repository.PositionRepository;
import com.khorunaliyev.khorunaliyev.repository.ProvinceRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

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
    public void setDistrict(@RequestParam("name") String name){
       District district = new District();
       district.setName(name);
       districtRepository.save(district);
    }


}
