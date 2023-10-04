package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {


    Optional<Student> findAllByEmailEqualsIgnoreCase(String email);
}
