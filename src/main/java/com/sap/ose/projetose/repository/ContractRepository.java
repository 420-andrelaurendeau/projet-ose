package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllByStudentId(long id);

}
