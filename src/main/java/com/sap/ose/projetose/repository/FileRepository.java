package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.models.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> { }