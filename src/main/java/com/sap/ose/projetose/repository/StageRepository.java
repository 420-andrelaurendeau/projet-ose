package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.modeles.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StageRepository extends JpaRepository<Stage, Long> {

    @Query("SELECT i FROM Stage i WHERE i.stateStudent = 1 AND i.student.id = ?1")
    Optional<List<Stage>> findAllStudentPending(long studentId);


    @Query("SELECT CASE " +
            "WHEN (s.stateStudent = 0 AND s.stateEmployeur = 0) THEN 'ACCEPTED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN 'PENDING' " +
            "ELSE 'DECLINED' " +
            "END, COUNT(s) " +
            "FROM Stage s " +
            "GROUP BY " +
            "CASE " +
            "WHEN (s.stateStudent = 0 AND s.stateEmployeur = 0) THEN 'ACCEPTED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN 'PENDING' " +
            "ELSE 'DECLINED' " +
            "END")
    List<Object[]> getCountByState();

    @Query("SELECT s FROM Stage s " +
            "WHERE (:state = 'ACCEPTED' AND s.stateStudent = 0 AND s.stateEmployeur = 0) " +
            "OR (:state = 'PENDING' AND (s.stateStudent = 1 OR s.stateEmployeur = 1)) " +
            "OR (:state = 'DECLINED')")
    Page<Stage> findAllByState(@Param("state") State state, Pageable pageable);

}
