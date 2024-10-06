package com.nisum.crudapi.h2.Prueba.services;

import com.nisum.crudapi.h2.Prueba.entities.Phone;
import com.nisum.crudapi.h2.Prueba.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PhoneService {

    @Autowired
    private PhoneRepository phoneRepository;

    public Phone createPhone(Phone phone){
        return  phoneRepository.save(phone);
    }

    public Phone getPhoneById(long id){
        Optional<Phone> optionalPhone = phoneRepository.findById(id);
        return  optionalPhone.get();
    }

    public List<Phone> getAllPhones(){
        return phoneRepository.findAll();
    }

    public void deletePhone(Long id){
        phoneRepository.deleteById(id);
    }
}
