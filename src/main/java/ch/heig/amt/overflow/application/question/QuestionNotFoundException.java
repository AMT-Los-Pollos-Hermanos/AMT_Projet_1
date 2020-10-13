package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.BusinessException;

public class QuestionNotFoundException extends BusinessException {

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
