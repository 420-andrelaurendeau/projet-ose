package com.sap.ose.projetose.dto;

public class studentHasInterviewWithInternOffer {
    public long studentId,internOfferId;
    public studentHasInterviewWithInternOffer(long studentId, long internOfferId) {
        this.studentId = studentId;
        this.internOfferId = internOfferId;
    }
}