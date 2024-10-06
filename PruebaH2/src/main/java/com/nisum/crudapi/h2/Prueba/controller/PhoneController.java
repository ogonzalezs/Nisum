package com.nisum.crudapi.h2.Prueba.controller;

import com.nisum.crudapi.h2.Prueba.entities.Phone;
import com.nisum.crudapi.h2.Prueba.services.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/phones")
public class PhoneController {

    @Autowired
    private PhoneService phoneService;

    @PostMapping()
    public Phone createPhone(@RequestBody Phone phone){
        return phoneService.createPhone(phone);
    }

    @GetMapping()
    public List<Phone> getAllPhones(){
        return phoneService.getAllPhones();
    }

    @GetMapping("{id}")
    public Phone searchUserById(@PathVariable Long id){
        return phoneService.getPhoneById(id);
    }

    @DeleteMapping("{id}")
    public void deletePhoneById(@PathVariable Long id){
        phoneService.deletePhone(id);
    }
}
