/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain.message;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class FlashMessage {

    String message;

    @Builder.Default
    String type = "success";

}
