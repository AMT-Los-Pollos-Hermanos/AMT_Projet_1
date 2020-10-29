/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain;

import java.util.UUID;

public class ContentId extends Id {
    public ContentId() {
        super();
    }

    public ContentId(String id) {
        super(id);
    }

    public ContentId(UUID id) {
        super(id);
    }
}
