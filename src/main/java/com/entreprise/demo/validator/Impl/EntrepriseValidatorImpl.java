package com.entreprise.demo.validator.Impl;

import com.entreprise.demo.exception.ValidationException;
import com.entreprise.demo.model.Entreprise;
import com.entreprise.demo.validator.EntrepriseValidator;
import org.springframework.stereotype.Component;


@Component("EntrepriseValidator")
public class EntrepriseValidatorImpl implements EntrepriseValidator {
    @Override
    public void beforeUpdate(Entreprise updateRequest) {
        if (updateRequest.getSiren().isEmpty()) {
            throw new ValidationException("Le siren est obligatoire");
        }
        if (updateRequest.getSiret().isEmpty()) {
            throw new ValidationException("Le siret est obligatoire");
        }
        if (updateRequest.getAddress().length() <5 || updateRequest.getAddress().length()>100) {
            throw new ValidationException("L'adrresse doit être compris entre 5 et 100 caractères");
        }
        if (updateRequest.getSocialReason().length() <5 || updateRequest.getSocialReason().length()>100) {
            throw new ValidationException("Le raison sociale doit être compris entre 5 et 100  caractères");
        }
    }

    @Override
    public void beforeSave(Entreprise createRequest) {
        if (createRequest.getSiren().isEmpty()) {
            throw new ValidationException("Le siren est obligatoire");
        }
        if (createRequest.getSiret().isEmpty()) {
            throw new ValidationException("Le siret est obligatoire");
        }
        if (createRequest.getAddress().length() <5 || createRequest.getAddress().length()>100) {
            throw new ValidationException("L'adrresse doit être compris entre 5 et 100 caractères");
        }
        if (createRequest.getSocialReason().length() <5 || createRequest.getSocialReason().length()>100) {
            throw new ValidationException("Le raison sociale doit être compris entre 5 et 100  caractères");
        }
    }
}
