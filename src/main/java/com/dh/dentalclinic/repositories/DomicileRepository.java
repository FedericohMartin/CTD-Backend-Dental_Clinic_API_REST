package com.dh.dentalclinic.repositories;

import com.dh.dentalclinic.entities.Domicile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomicileRepository extends JpaRepository<Domicile, Long> {
}
