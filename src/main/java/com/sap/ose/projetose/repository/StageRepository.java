package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.modeles.Stage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query("SELECT i FROM Stage i WHERE i.stateStudent = 1 AND i.student.id = ?1")
    Optional<List<Stage>> findAllStudentPending(long studentId);


}
