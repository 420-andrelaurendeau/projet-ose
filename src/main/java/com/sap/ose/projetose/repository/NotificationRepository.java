package com.sap.ose.projetose.repository;

import com.sap.ose.projetose.modeles.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Stack;

@Repository
public interface NotificationRepository extends JpaRepository<Notifications,Long> {
    
    Stack<Notifications> findByReceveurs_idOrderByIdDesc(long id);
}
