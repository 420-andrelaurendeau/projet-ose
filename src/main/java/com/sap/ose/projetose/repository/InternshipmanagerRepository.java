package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Internshipmanager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InternshipmanagerRepository extends JpaRepository<Internshipmanager, Long> {
    Internshipmanager findByStudyPrograms_Id(long id);
}
