package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.ContractDto;
import com.sap.ose.projetose.modeles.Contract;
import com.sap.ose.projetose.modeles.Stage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
     Page<Contract> findAllByStudentId(long id, Pageable pageable);

}
