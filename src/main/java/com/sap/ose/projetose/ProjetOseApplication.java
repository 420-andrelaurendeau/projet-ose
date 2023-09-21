package com.sap.ose.projetose;

import com.sap.ose.projetose.dto.EmployeurDTO;
import com.sap.ose.projetose.modeles.Employeur;
import com.sap.ose.projetose.repository.EmployeurRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetOseApplication implements CommandLineRunner {

	@Autowired
	EmployeurRepositary employeurRepositary;

	public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}

	public void run(String...args) throws Exception{
		//Employeur employeur1 = employeurRepositary.save(new Employeur("mehdi","intels"));
	}
}
