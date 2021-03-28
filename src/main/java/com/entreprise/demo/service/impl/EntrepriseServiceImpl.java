package com.entreprise.demo.service.impl;

import com.entreprise.demo.exception.ResourceNotFoundException;
import com.entreprise.demo.model.Entreprise;
import com.entreprise.demo.repository.EntrepriseRepository;
import com.entreprise.demo.service.EntrepriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EntrepriseServiceImpl implements EntrepriseService {

    @Autowired
    private EntrepriseRepository entrepriseRepository;

    private final Logger log = LoggerFactory.getLogger(EntrepriseServiceImpl.class);

    @Override
    public Entreprise getEntrepriseById(Long id) {
        log.debug("request to find entreprise by id {}", id);
        return entrepriseRepository.getOne(id);
    }

    @Override
    public Page<Entreprise> getAllEntreprises(Pageable pageable) {
        log.debug("request to get all entreprises");
        return entrepriseRepository.findAll(pageable);
    }

    @Override
    public Entreprise createEntreprise(Entreprise entreprise) {
        log.debug("request to create new entreprise {}", entreprise);
        return entrepriseRepository.save(entreprise);
    }

    @Override
    public Entreprise updateEntreprise(Entreprise entrepriseRequest) {
        log.debug("request to update entreprise {},{}", entrepriseRequest,entrepriseRequest.getId());
        return entrepriseRepository.findById(entrepriseRequest.getId()).map(entreprise -> {
            entreprise.setAddress(entrepriseRequest.getAddress());
            entreprise.setSiren(entrepriseRequest.getSiren());
            entreprise.setSiret(entrepriseRequest.getSiret());
            entreprise.setSocialReason(entrepriseRequest.getSocialReason());
            entreprise.setCreatedAt(new Date());
            return entrepriseRepository.save(entreprise);
        }).orElseThrow(() -> new ResourceNotFoundException("entrepriseId " + entrepriseRequest.getId() + " not found"));    }

    @Override
    public Entreprise deleteEntreprise(Long id) {
        log.debug("request to delete entreprise {} " , id);
        return entrepriseRepository.findById(id).map(entreprise -> {
            entrepriseRepository.delete(entreprise);
            return entreprise;
        }).orElseThrow(() -> new ResourceNotFoundException("entrepriseId " + id + " not found"));
    }
}
