package com.sap.ose.projetose.exceptions;

import lombok.Getter;
import org.apache.catalina.User;

@Getter
public class UserNotSavedException extends DatabaseException {
    private final User user;

    public UserNotSavedException(User user) {
        super("Ne Pouvait pas sauvgarder l'utilisateur: " + user.toString());
        this.user = user;
    }
}


