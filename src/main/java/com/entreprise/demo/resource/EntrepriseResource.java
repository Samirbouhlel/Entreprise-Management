package com.entreprise.demo.resource;


import com.entreprise.demo.model.Entreprise;
import com.entreprise.demo.service.EntrepriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Entreprise")
@RestController
public class EntrepriseResource {

    @Autowired
    private EntrepriseService entrepriseService;

    @GetMapping("/ById/{entrepriseId}")
    public Entreprise getEntrepriseById(@PathVariable Long entrepriseId) {
        return entrepriseService.getEntrepriseById(entrepriseId);
    }
    @GetMapping("/all")
    public Page<Entreprise> getAllEntreprises(Pageable pageable) {
        return entrepriseService.getAllEntreprises(pageable);
    }

    @PostMapping("/create")
    public Entreprise createPost(@RequestBody Entreprise entreprise) {
        return entrepriseService.createEntreprise(entreprise);
    }

    @PutMapping("/update")
    public Entreprise updateEntreprise(@RequestBody Entreprise entrepriseRequest) {
        return entrepriseService.updateEntreprise(entrepriseRequest);
    }

    @DeleteMapping("/delete/{entrepriseId}")
    public Entreprise deleteEntreprise(@PathVariable Long entrepriseId) {
       return entrepriseService.deleteEntreprise(entrepriseId);
    }

}
