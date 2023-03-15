package com.khorunaliyev.khorunaliyev.service;

import com.khorunaliyev.khorunaliyev.entity.*;
import com.khorunaliyev.khorunaliyev.model.AnyResult;
import com.khorunaliyev.khorunaliyev.repository.DistrictRepository;
import com.khorunaliyev.khorunaliyev.repository.OrganizationRepository;
import com.khorunaliyev.khorunaliyev.repository.ProvinceRepository;
import com.khorunaliyev.khorunaliyev.repository.UsersRepository;
import com.khorunaliyev.khorunaliyev.security.CustomeUserDetailsService;
import com.khorunaliyev.khorunaliyev.security.TokenGenerator;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrganizationServices {

    private final OrganizationRepository organizationRepository;
    private final ProvinceRepository provinceRepository;
    private final DistrictRepository districtRepository;
    private final TokenGenerator tokenGenerator;
    private final CustomeUserDetailsService userDetailsService;

    private final UsersRepository usersRepository;


    public ResponseEntity<AnyResult> organizationSave(Long p_id,
                                                      Long d_id,
                                                      String address,
                                                      String firstname,
                                                      String lastname,
                                                      String thirdname,
                                                      String stir,
                                                      String email,
                                                      String phone,
                                                      String bankAccount,
                                                      HttpServletRequest request) {
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(tokenGenerator.
                        getUsernameByToken(tokenGenerator.
                                getTokenFromRequest(request)));


        final Users users = usersRepository.findByUsername(userDetails.getUsername()).orElseThrow();
        final Roles roles = users.getRoles().stream().findFirst().orElseThrow();

        Province province;
        District district;
        final Organization organization = new Organization();
        if (roles.getName().equals("ROLE_ADMIN")) {


            final Optional<Province> provinceOptional = provinceRepository.findById(p_id);
            if (provinceOptional.isPresent()) {
                province = provinceOptional.get();
                organization.setProvince(province);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Bunday viloyat mavjud emas"));
            }
            final Optional<District> districtOptional = districtRepository.findById(d_id);
            if (districtOptional.isPresent()) {
                district = districtOptional.get();
                organization.setDistrict(district);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Bunday tuman mavjud emas"));
            }
            if (!address.isEmpty()) {
                organization.setAddress(address);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Manzil kiritilmadi"));
            }
            if (firstname.trim().length() <= 100 && firstname.trim().length() > 1) {
                organization.setChiefFirstName(firstname);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Ism to'g'ri kiritilmadi"));
            }
            if (lastname.trim().length() <= 100 && lastname.trim().length() > 1) {
                organization.setChiefLastName(lastname);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Familiya to'g'ri kiritilmadi"));
            }
            if (thirdname.trim().length() <= 100 && thirdname.trim().length() > 1) {
                organization.setChiefThirdName(thirdname);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "Otasining ismi to'g'ri kiritilmadi"));
            }
            if (stir.length() == 9) {
                organization.setSTIR(stir);
            } else {
                return ResponseEntity.ok(new AnyResult(false, "STIR kiritishda muammo"));
            }
            if (phone.length() == 13) {
                final Optional<Organization> phoneCheck = organizationRepository.findByPhone(phone);
                if(phoneCheck.isPresent()){
                    organization.setPhone(phone);
                }
                else {
                    return ResponseEntity.ok(new AnyResult(false, "Kechirasiz telefon raqam tizimda mavjud"));
                }

            } else {
                return ResponseEntity.ok(new AnyResult(false, "Raqam kiritishda muammo"));
            }
            if (email != null) {
                final Optional<Organization> organizationEmailCheck = organizationRepository.findByEmail(email);
                if (organizationEmailCheck.isPresent()) {
                    organization.setEmail(email);
                } else {
                    return ResponseEntity.ok(new AnyResult(false, "Kechirasiz bunday elektron pochta tizimda mavjud"));
                }
            }
            if(bankAccount!=null){
                final Optional<Organization> organizationBankAccountCheck = organizationRepository.findByBankAccount(bankAccount);
                if(organizationBankAccountCheck.isPresent()){
                    organization.setBankAccount(bankAccount);
                }
                else {
                    return ResponseEntity.ok(new AnyResult(false, "Kechirasiz bank hisob raqami tizimda mavjud"));
                }

            }

        } else {
            return ResponseEntity.ok(new AnyResult(false, "Siz tizim administratori emassiz"));
        }

        organization.setEmail(email);
        organization.setBankAccount(bankAccount);
        organizationRepository.save(organization);
        return ResponseEntity.ok(new AnyResult(true, "Barcha ma'lumotlar saqlandi"));

    }

    public ResponseEntity<Collection<Organization>> getAll() {
        return ResponseEntity.ok(organizationRepository.findAll());
    }
}
