/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.question;

import ch.heig.amt.overflow.application.BusinessException;

public class QuestionNotFoundException extends BusinessException {

    public QuestionNotFoundException(String message) {
        super(message);
    }

}
