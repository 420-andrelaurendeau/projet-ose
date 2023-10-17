package com.sap.ose.projetose;

import com.sap.ose.projetose.models.Employer;
import com.sap.ose.projetose.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

@SpringBootApplication
@RequiredArgsConstructor
public class ProjetOseApplication implements CommandLineRunner {
    private final EmployerRepository employerRepository;

    public static void main(String[] args) {
		SpringApplication.run(ProjetOseApplication.class, args);
	}
    @Override
    public void run(String... args) throws Exception {
        employerRepository.save(new Employer());
    }
}
