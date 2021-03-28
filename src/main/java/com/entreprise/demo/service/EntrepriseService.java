package com.entreprise.demo.service;

import com.entreprise.demo.model.Entreprise;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EntrepriseService {
    Entreprise getEntrepriseById(Long id);
    Page<Entreprise> getAllEntreprises(Pageable pageable);
    Entreprise createEntreprise(Entreprise entreprise);
    Entreprise updateEntreprise(Entreprise entreprise);
    Entreprise deleteEntreprise(Long id);
}
