package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileEntityRepository extends JpaRepository<File, Long> {
    @Query("select f from File f where f.isAccepted = com.sap.ose.projetose.modeles.State.PENDING and f.etudiant is not null")
    Optional<List<File>> findAllStudentCvPending();
    @Query("select f from File f where f.etudiant.id = ?1")
    Optional<List<File>> findAllCvByEtudiantId(long id);
}
