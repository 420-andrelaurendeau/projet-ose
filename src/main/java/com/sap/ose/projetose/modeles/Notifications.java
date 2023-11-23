package com.sap.ose.projetose.modeles;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

    @Entity
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class Notifications {
        @Id
        private Long id;
        @OneToOne
        private Utilisateur receveurs;
        @OneToOne
        private Programme programme;
        private Notificationsi18n message;
        private boolean isRead;
    }
