package com.sap.ose.projetose.exception;

public class NotificationNotFoundException extends Exception {
    public NotificationNotFoundException() {
        super("Notification n'est pas trouvé");
    }
}
