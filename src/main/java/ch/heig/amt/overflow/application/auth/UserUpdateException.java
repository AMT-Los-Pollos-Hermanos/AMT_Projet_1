package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.application.BusinessException;

public class UserUpdateException extends BusinessException {

    public UserUpdateException(String message) {
        super(message);
    }
}
