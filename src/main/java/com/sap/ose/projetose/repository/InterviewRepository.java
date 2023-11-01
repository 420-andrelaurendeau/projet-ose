package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    @Query("SELECT i FROM Interview i WHERE i.student.id = ?1 AND i.internshipOffer.id = ?2")
    Interview findByStudentIdAndInternOfferId(long studentId, long internOfferId);

}
