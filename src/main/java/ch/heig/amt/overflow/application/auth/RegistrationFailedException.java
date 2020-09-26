package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.application.BusinessException;

public class RegistrationFailedException extends BusinessException {

    public RegistrationFailedException(String message) {
        super(message);
    }
}
