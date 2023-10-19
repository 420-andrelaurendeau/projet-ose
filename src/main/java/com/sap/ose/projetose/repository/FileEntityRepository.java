package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileEntityRepository extends JpaRepository<File, Long> {
}
