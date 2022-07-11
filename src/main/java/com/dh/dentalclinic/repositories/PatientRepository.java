package com.dh.dentalclinic.repositories;

import com.dh.dentalclinic.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    @Query("Select p From Patient p where p.email=?1")
    Optional<Patient> findPatientByEmail(String email);
}
