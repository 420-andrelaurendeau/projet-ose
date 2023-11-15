package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.File;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FileEntityRepository extends JpaRepository<File, Long> {
    @Query("select f from File f where f.isAccepted = com.sap.ose.projetose.modeles.State.PENDING and f.etudiant is not null")
    Optional<List<File>> findAllStudentCvPending();

    @Query("select f from File f where f.etudiant.id = ?1")
    Optional<File> findByEtudiant_Id(Long id);


    @Transactional
    @Modifying
    @Query("update File f set f.content = ?1, f.fileName = ?2 where f.id = ?3")
    void updateFileById(byte[] content, String fileName, Long id);

}
