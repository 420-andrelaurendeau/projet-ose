package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.TemplateContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TemplateContractRepository extends JpaRepository<TemplateContract, Long> {

    @Query(value = " SELECT t FROM TemplateContract t WHERE t.isActive = true")
    TemplateContract findTemplateContractByIsActive();
}
