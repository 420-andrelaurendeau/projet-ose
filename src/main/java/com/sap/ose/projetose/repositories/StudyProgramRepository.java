package com.sap.ose.projetose.repositories;

import com.sap.ose.projetose.models.StudyProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyProgramRepository extends JpaRepository<StudyProgram, Long> {
}
