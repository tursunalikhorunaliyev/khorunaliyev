package com.khorunaliyev.khorunaliyev.controller;

import com.khorunaliyev.khorunaliyev.entity.Organization;
import com.khorunaliyev.khorunaliyev.extra.SecurityVariables;
import com.khorunaliyev.khorunaliyev.model.AnyResult;
import com.khorunaliyev.khorunaliyev.repository.OrganizationRepository;
import com.khorunaliyev.khorunaliyev.service.OrganizationServices;
import jdk.jfr.Frequency;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

@RestController
@RequestMapping(SecurityVariables.URL_DIRECTION+"/organization")
@AllArgsConstructor
public class OrganizationController {
    private final OrganizationServices organizationServices;


    @PostMapping("save")
    public ResponseEntity<AnyResult> saveOrganization(@RequestParam("province_id") Long p_id,
                                                      @RequestParam("district_id") Long d_id,
                                                      @RequestParam("address") String address,
                                                      @RequestParam("firstname") String firstname,
                                                      @RequestParam("lastname") String lastname,
                                                      @RequestParam("thirdname") String thirdname,
                                                      @RequestParam("stir") String stir,
                                                      @RequestParam(value = "email", required = false) String email,
                                                      @RequestParam("phone") String phone,
                                                      @RequestParam(value = "bank_account", required = false) String bankAccount,
                                                      HttpServletRequest request
    ){
        return organizationServices.organizationSave(p_id, d_id, address, firstname, lastname, thirdname, stir, email, phone, bankAccount, request);
    }

    @GetMapping("all")
    public ResponseEntity<Collection<Organization>> getAllOrganizations(){
        return organizationServices.getAll();
    }

}
