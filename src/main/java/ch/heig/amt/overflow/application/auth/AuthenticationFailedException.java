package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.application.BusinessException;

public class AuthenticationFailedException extends BusinessException {

    public AuthenticationFailedException(String message) {
        super(message);
    }

}
