/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.auth;

import ch.heig.amt.overflow.application.BusinessException;

public class ChangePasswordException extends BusinessException {

    public ChangePasswordException(String message) {
        super(message);
    }

}
