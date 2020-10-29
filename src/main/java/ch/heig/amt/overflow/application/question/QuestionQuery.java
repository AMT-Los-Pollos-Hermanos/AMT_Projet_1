/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.application.question;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class QuestionQuery {

    @Builder.Default
    String search = "";

}
