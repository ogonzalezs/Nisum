package com.nisum.crudapi.h2.Prueba.repositories;

import com.nisum.crudapi.h2.Prueba.entities.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long> {
}
