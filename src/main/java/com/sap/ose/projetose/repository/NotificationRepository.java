package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications,Long> {

    @Query("SELECT n FROM Notifications n WHERE n.receveurs.id = ?1")
    List<Notifications> findByReceveurs_id(long id);
}
