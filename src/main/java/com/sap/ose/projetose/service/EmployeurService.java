package com.sap.ose.projetose.service;

import com.sap.ose.projetose.model.Employeur;
import org.springframework.stereotype.Service;
import com.sap.ose.projetose.repository.EmployeurRepository;

@Service
public class EmployeurService {

    private final EmployeurRepository employeurRepository;

    public EmployeurService(EmployeurRepository employeurRepository) {
        this.employeurRepository = employeurRepository;
    }

    public Employeur saveEmployeur(Employeur employeur) {
        return employeurRepository.save(employeur);
    }


}
