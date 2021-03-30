package com.entreprise.demo.validator.Impl;

import com.entreprise.demo.enumeration.ContractType;
import com.entreprise.demo.exception.ValidationException;
import com.entreprise.demo.model.Employe;
import com.entreprise.demo.repository.EmployeRepository;
import com.entreprise.demo.repository.EntrepriseRepository;
import com.entreprise.demo.validator.EmployeValidator;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("EmployeValidator")
public class EmployeValidatorImpl implements EmployeValidator{

    private final EmployeRepository employeRepository;
    private final EntrepriseRepository entrepriseRepository;

    public EmployeValidatorImpl(EmployeRepository employeRepository,
                                EntrepriseRepository entrepriseRepository) {
        this.employeRepository = employeRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    @Override
    public void beforeUpdate(Employe updateRequest) {
        Employe oldEmploye = employeRepository.getOne(updateRequest.getId());
        if (updateRequest.getFirstName().isEmpty()) {
            throw new ValidationException("Le nom est obligatoire");
        }
        if (updateRequest.getFirstName().length() <5 || updateRequest.getFirstName().length()>100) {
            throw new ValidationException("Le nom doit être compris entre 5 et 100 caractères");
        }
        if (updateRequest.getLastName().isEmpty()) {
            throw new ValidationException("Le prénom est obligatoire");
        }
        if (updateRequest.getLastName().length() <5 || updateRequest.getLastName().length()>100) {
            throw new ValidationException("Le nom doit être compris entre 5 et 100  caractères");
        }
        if (updateRequest.getSocialSecurityNumber().isEmpty()) {
            throw new ValidationException("Le numéro de sécurité sociale est obligatoire");
        }
        if (updateRequest.getHiringDate() == null) {
            throw new ValidationException("Le date d’embauche est obligatoire");
        }
        if (updateRequest.getContractType() == null) {
            throw new ValidationException("Le type de contrat est obligatoire");
        }
        if((oldEmploye.getContractType().equals(ContractType.CDD) ||
                oldEmploye.getContractType().equals(ContractType.CDI) &
                        updateRequest.getContractType().equals(ContractType.ALTERNANCE))){
            throw new ValidationException("un employé ne peut pas changer de contrat CDI ou CDD vers alternance");

        }
        if (updateRequest.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le salaire doit être supérieur à zéro");
        }


    }

    @Override
    public void beforeSave(Employe createRequest) {
        if (createRequest.getFirstName().isEmpty()) {
            throw new ValidationException("Le nom est obligatoire");
        }
        if (createRequest.getFirstName().length() <5 || createRequest.getFirstName().length()>100) {
            throw new ValidationException("Le nom doit être compris entre 5 et 100 caractères");
        }
        if (createRequest.getLastName().isEmpty()) {
            throw new ValidationException("Le prénom est obligatoire");
        }
        if (createRequest.getLastName().length() <5 || createRequest.getLastName().length()>100) {
            throw new ValidationException("Le nom doit être compris entre 5 et 100  caractères");
        }
        if (createRequest.getSocialSecurityNumber().isEmpty()) {
            throw new ValidationException("Le numéro de sécurité sociale est obligatoire");
        }
        if (createRequest.getHiringDate() == null) {
            throw new ValidationException("Le date d’embauche est obligatoire");
        }
        if (createRequest.getContractType() == null) {
            throw new ValidationException("Le type de contrat est obligatoire");
        }
        if (createRequest.getSalary().compareTo(BigDecimal.ZERO) < 0) {
            throw new ValidationException("Le salaire doit être supérieur à zéro");
        }

    }

}
