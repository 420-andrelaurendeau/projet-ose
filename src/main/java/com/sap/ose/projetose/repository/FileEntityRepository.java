package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileEntityRepository extends JpaRepository<File, Long> {
    @Query("select f from File f where f.isAccepted = false and f.etudiant is not null")
    Optional<List<File>> findAllStudentCvPending();
}
