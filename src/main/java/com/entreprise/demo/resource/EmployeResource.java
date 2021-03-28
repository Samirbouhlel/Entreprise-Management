package com.entreprise.demo.resource;

import com.entreprise.demo.model.Employe;
import com.entreprise.demo.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Employe")
@RestController
public class EmployeResource {
    @Autowired
    private EmployeService employeService;

    @GetMapping("/ById/{employeId}")
    public Employe getEmployeById(@PathVariable Long employeId) {
        return employeService.getEmployeById(employeId);
    }

    @GetMapping("/ByEntrepriseId/{EntrepriseId}")
    public Page<Employe> getEmployesByEntrepriseId(@PathVariable Long EntrepriseId, Pageable pageable) {
        return employeService.getEmployesByEntrepriseId(EntrepriseId, pageable);
    }

    @GetMapping("/all")
    public Page<Employe> getAllEmployes(Pageable pageable) {
        return employeService.getAllEmployes(pageable);
    }

    @PostMapping("/create/{EntrepriseId}")
    public Employe createEmploye(@PathVariable Long EntrepriseId, @RequestBody Employe employe) {
        return employeService.createEmploye(EntrepriseId, employe);
    }

    @PutMapping("/update/{EntrepriseId}")
    public Employe updateEmploye(@PathVariable Long EntrepriseId, @RequestBody Employe employeRequest) {
        return employeService.updateEmploye(EntrepriseId, employeRequest);
    }

    @DeleteMapping("/delete/{entrepriseId}/{EmployeId}")
    public Employe deleteEmploye(@PathVariable Long entrepriseId, @PathVariable Long EmployeId) {
        return employeService.deleteEmploye(entrepriseId, EmployeId);
    }
}