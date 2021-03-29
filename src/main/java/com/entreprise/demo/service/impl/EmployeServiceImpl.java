package com.entreprise.demo.service.impl;

import com.entreprise.demo.exception.ResourceNotFoundException;
import com.entreprise.demo.model.Employe;
import com.entreprise.demo.repository.EmployeRepository;
import com.entreprise.demo.repository.EntrepriseRepository;
import com.entreprise.demo.service.EmployeService;
import com.entreprise.demo.validator.EmployeValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class EmployeServiceImpl implements EmployeService {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private EntrepriseRepository entrepriseRepository;
    @Autowired
    private EmployeValidator employeValidator;
    private final Logger log = LoggerFactory.getLogger(EmployeServiceImpl.class);


    @Override
    public Employe getEmployeById(Long id) {
        log.debug("request to find employe by id {}", id);
        return employeRepository.getOne(id);
    }

    @Override
    public Page<Employe> getEmployesByEntrepriseId(Long EntrepriseId, Pageable pageable) {
        log.debug("request to find employe by entreprise id {}", EntrepriseId);
        return employeRepository.findAllByEntrepriseId(EntrepriseId,pageable);
    }

    @Override
    public Page<Employe> getAllEmployes(Pageable pageable) {
        log.debug("request to get all employees");
        return employeRepository.findAll(pageable);    }

    @Override
    public Employe createEmploye(Long EntrepriseId, Employe employe) {
        log.debug("request to create new employe By Entreprise id {} , {}", employe, EntrepriseId);
        return entrepriseRepository.findById(EntrepriseId).map(entreprise -> {
            employe.setEntreprise(entreprise);
            employeValidator.beforeSave(employe);
            return employeRepository.save(employe);
        }).orElseThrow(() -> new ResourceNotFoundException("Entreprise " + EntrepriseId + " not found"));
    }

    @Override
    public Employe updateEmploye(Long EntrepriseId, Employe employeRequest) {
        log.debug("request to update employe By Entreprise id {} , {}", employeRequest, EntrepriseId);
        if(!entrepriseRepository.existsById(EntrepriseId)) {
            throw new ResourceNotFoundException("Entreprise " + EntrepriseId + " not found");
        }
        return employeRepository.findById(employeRequest.getId()).map(employe -> {
            employe.setContractType(employeRequest.getContractType());
            employe.setFirstName(employeRequest.getFirstName());
            employe.setLastName(employeRequest.getLastName());
            employe.setEntreprise(employeRequest.getEntreprise());
            employe.setSocialSecurityNumber(employeRequest.getSocialSecurityNumber());
            employe.setHiringDate(employeRequest.getHiringDate());
            employe.setSalary(employeRequest.getSalary());
            employeValidator.beforeUpdate(employeRequest);
            return employeRepository.save(employe);
        }).orElseThrow(() -> new ResourceNotFoundException("employeId " + employeRequest.getId() + "not found"));    }

    @Override
    public Employe deleteEmploye(Long EntrepriseId, Long EmployeId) {
        log.debug("request to delete employe By Entreprise id {} , {}", EmployeId, EntrepriseId);
        return employeRepository.findByIdAndEntrepriseId(EntrepriseId, EmployeId).map(employe -> {
            employeRepository.delete(employe);
            return employe;
        }).orElseThrow(() -> new ResourceNotFoundException("Employe not found with id " + EmployeId + " and EntrepriseId " + EntrepriseId));
    }

    @Override
    public BigDecimal getSalaryByEntrepriseIdAndContractType(Long entrepriseId, String contractType, String grille) {
        BigDecimal salary = null;
        if(grille.equals("min")){
            salary = employeRepository.min(entrepriseId,contractType);
        }
        if (grille.equals("max")){
            salary = employeRepository.max(entrepriseId,contractType);
        }
        if (grille.equals("moy")){
            salary = employeRepository.moyen(entrepriseId,contractType);
        }
        return salary;
    }

    @Override
    public Page<Employe> filterEmployes(String search, Pageable pageable) {
        return employeRepository.filterEmployes(search,pageable);
    }
}
