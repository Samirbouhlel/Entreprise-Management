package com.entreprise.demo.repository;

import com.entreprise.demo.model.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeRepository extends JpaRepository<Employe, Long> {
    Optional<Employe> findByIdAndEntrepriseId(Long entrepriseId, Long employeId);
    Page<Employe> findAllByEntrepriseId(Long entrepriseId , Pageable pageable);
}
