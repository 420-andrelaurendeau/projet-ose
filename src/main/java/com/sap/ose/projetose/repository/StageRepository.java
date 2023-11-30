package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Interview;
import com.sap.ose.projetose.modeles.Stage;
import com.sap.ose.projetose.modeles.State;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    @Query("SELECT i FROM Stage i WHERE i.student.id = :id AND (:session IS NULL OR :session = '' OR i.offer.session = :session)")
    Page<Stage> findAllByStudentPendingPage(@Param("id") long id, @Param("session") String session,Pageable pageable);

    @Query("SELECT i FROM Stage i WHERE i.id = ?1")
    Optional<Stage> findById(long id);

    @Query("SELECT CASE " +
            "WHEN (s.stateStudent = 2 OR s.stateEmployeur = 2) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN 'PENDING' " +
            "ELSE 'ACCEPTED' " +
            "END, COUNT(s) " +
            "FROM Stage s " +
            "WHERE (:session IS NULL OR :session = '' OR s.offer.session = :session)" +
            "GROUP BY " +
            "CASE " +
            "WHEN (s.stateStudent = 2 OR s.stateEmployeur = 2) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN 'PENDING' " +
            "ELSE 'ACCEPTED' " +
            "END")
    List<Object[]> getCountByState(@Param("session") String session);

    @Query("SELECT CASE " +
            "WHEN (s.stateStudent = 2 OR s.stateEmployeur = 2) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1)THEN 'PENDING' " +
            "ELSE 'ACCEPTED' " +
            "END, COUNT(s) " +
            "FROM Stage s " +
            "WHERE (s.employeur.id = :id) " +
            "GROUP BY " +
            "CASE " +
            "WHEN (s.stateStudent = 2 OR s.stateEmployeur = 2) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN 'PENDING' " +
            "ELSE 'ACCEPTED' " +
            "END")
    List<Object[]> getCountByStateByEmployeur(long id);

    @Query("SELECT CASE " +
            "WHEN (s.stateStudent = 2 AND  s.stateEmployeur = 0) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 AND s.stateEmployeur = 0)THEN 'PENDING' " +
            "WHEN (s.stateStudent = 0 AND s.stateEmployeur = 0) THEN 'ACCEPTED' "+
            "ELSE 'IRRELEVANT'" +
            "END, COUNT(s) " +
            "FROM Stage s " +
            "WHERE (s.student.id = :id) " +
            "GROUP BY " +
            "CASE " +
            "WHEN (s.stateStudent = 2 AND s.stateEmployeur = 0) THEN 'DECLINED' " +
            "WHEN (s.stateStudent = 1 AND s.stateEmployeur = 0) THEN 'PENDING' " +
            "WHEN (s.stateStudent = 0 AND s.stateEmployeur = 0) THEN 'ACCEPTED' "+
            "ELSE 'IRRELEVANT'" +
            "END")
    List<Object[]> getCountByStateByStudent(long id);

    @Query("SELECT s FROM Stage s " +
            "WHERE ( (:state = 'DECLINED') AND (s.stateStudent = 2 OR s.stateEmployeur = 2)) " +
            "OR (:state = 'PENDING' AND ((s.stateStudent = 1 OR s.stateEmployeur = 1) AND (s.stateStudent != 2 AND s.stateEmployeur != 2))) " +
            "OR (:state = 'ACCEPTED' AND (s.stateStudent = 0 AND s.stateEmployeur = 0) )"
    )
    Page<Stage> findAllByState(@Param("state") String state, Pageable pageable);


    @Query("SELECT s FROM Stage s " +
            "WHERE ( (:state = 'DECLINED') AND (s.stateStudent = 2 OR s.stateEmployeur = 2)) AND (s.employeur.id = :id) " +
            "OR (:state = 'PENDING' AND ((s.stateStudent = 1 OR s.stateEmployeur = 1) AND (s.stateStudent != 2 AND s.stateEmployeur != 2) AND (s.employeur.id = :id)))" +
            "OR (:state = 'ACCEPTED' AND (s.stateStudent = 0 AND s.stateEmployeur = 0) AND (s.employeur.id = :id) )")
    Page<Stage> findAllByStateEmployeur(@Param("state") String state, Pageable pageable, long id);

    @Query("SELECT s FROM Stage s WHERE (s.employeur.id = :id)")
    Page<Stage> findAllByEmployeurId(long id, Pageable pageable);

    @Query("SELECT s FROM Stage s WHERE (s.student.id = :id) AND (s.stateEmployeur = 0) AND (:session IS NULL OR :session = '' OR s.offer.session = :session)")
    Page<Stage> findAllByStudentId(long id, Pageable pageable, @Param("session") String session);

    @Query("SELECT s FROM Stage s " +
            "WHERE ( (:state = 'DECLINED') AND (s.stateStudent = 2 OR s.stateEmployeur = 0)) AND (s.student.id = :id) AND (:session IS NULL OR :session = '' OR s.offer.session = :session)" +
            "OR (:state = 'PENDING' AND ((s.stateStudent = 1 OR s.stateEmployeur = 0) AND (s.stateStudent != 2 AND s.stateEmployeur != 2) AND (s.student.id = :id))) AND (:session IS NULL OR :session = '' OR s.offer.session = :session)" +
            "OR (:state = 'ACCEPTED' AND (s.stateStudent = 0 AND s.stateEmployeur = 0) AND (s.student.id = :id) ) AND (:session IS NULL OR :session = '' OR s.offer.session = :session)")
    Page<Stage> findAllByStateStudent(@Param("state") String state, Pageable pageable, long id, @Param("session") String session);

    @Query("SELECT CASE " +
            "WHEN (s.stateStudent = 2 OR s.stateEmployeur = 2) THEN false " + // 'DECLINED'
            "WHEN (s.stateStudent = 1 OR s.stateEmployeur = 1) THEN false " + // 'PENDING'
            "ELSE true END " + // 'ACCEPTED'
            "FROM Stage s WHERE s.id = :id")
    boolean isContractAccepted(@Param("id") long id);

    @Query("select s from Stage s where s.id = :id")
    Optional<Stage> findStageById(@Param("id") long id);


    @Query("SELECT s FROM Stage s WHERE (:session IS NULL OR :session = '') OR (s.offer.session = :session)")
    Page<Stage> findAllBySession(Pageable pageable,@Param("session") String session);

    @Query("SELECT s FROM Stage s WHERE ( (:state = 'DECLINED') AND (s.stateStudent = 2 OR s.stateEmployeur = 2)) OR (:state = 'PENDING' AND ((s.stateStudent = 1 OR s.stateEmployeur = 1) AND (s.stateStudent != 2 AND s.stateEmployeur != 2))) " +
            "OR (:state = 'ACCEPTED' AND (s.stateStudent = 0 AND s.stateEmployeur = 0)) AND ((:session IS NULL OR :session = '') OR (s.offer.session = :session))")
    Page<Stage> findAllByStateAndSession(@Param("state") String state ,Pageable pageable,@Param("session") String session);

    @Query("SELECT s FROM Stage s WHERE (s.employeur.id = :id) AND (:session IS NULL OR :session = '' OR s.offer.session = :session)")
    Page<Stage> findAllSessionByEmployer(@Param("id") long id,@Param("session") String session,Pageable pageable );

    @Query("SELECT s FROM Stage s WHERE (s.employeur.id = :id) AND ( (:state = 'DECLINED') AND (s.stateStudent = 2 OR s.stateEmployeur = 2)) OR (:state = 'PENDING' AND ((s.stateStudent = 1 OR s.stateEmployeur = 1) AND (s.stateStudent != 2 AND s.stateEmployeur != 2))) " +
            "OR (:state = 'ACCEPTED' AND (s.stateStudent = 0 AND s.stateEmployeur = 0)) AND ((:session IS NULL OR :session = '') OR (s.offer.session = :session))")
    Page<Stage> findAllStateAndSessionByEmployer(@Param("state") String state ,@Param("session") String session,@Param("id")long id,Pageable pageable);


}
