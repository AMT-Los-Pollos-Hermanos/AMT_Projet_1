/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.question;

import ch.heig.amt.overflow.domain.MainContentId;

import java.util.UUID;

public class QuestionId extends MainContentId {

    public QuestionId() {
        super();
    }

    public QuestionId(String id) {
        super(id);
    }

    public QuestionId(UUID id) {
        super(id);
    }

}
