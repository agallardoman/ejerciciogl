package com.globallogic.ejerciciogl.repository;

import com.globallogic.ejerciciogl.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<Phone, Long>{

}
