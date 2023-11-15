package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.dto.InterviewDTO;
import com.sap.ose.projetose.modeles.Interview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("SELECT i FROM Interview i WHERE i.state = 1 AND i.student.id = ?1")
    Optional<List<Interview>> findAllPending(long studentId);
    @Query("SELECT i FROM Interview i WHERE i.student.id = :studentId")
    Page<Interview> findAllByStudentId(long studentId , Pageable pageable);

    @Query("SELECT i FROM Interview i WHERE i.student.id = ?1 AND i.internshipOffer.id = ?2")
    Interview findByStudentIdAndInternOfferId(long studentId, long internOfferId);


}
