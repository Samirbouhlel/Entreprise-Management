package com.entreprise.demo.service;

import com.entreprise.demo.model.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface EmployeService {
    Employe getEmployeById(Long id);
    Page<Employe> getEmployesByEntrepriseId(Long EntrepriseId,Pageable pageable);
    Page<Employe> getAllEmployes(Pageable pageable);
    Employe createEmploye(Long EntrepriseId, Employe employe);
    Employe updateEmploye(Long EntrepriseId, Employe employe);
    Employe deleteEmploye(Long EntrepriseId, Long EmployeId);
    BigDecimal getSalaryByEntrepriseIdAndContractType(Long entrepriseId,String contractType,String grille);
    Page<Employe> filterEmployes(String search, Pageable pageable);
}
