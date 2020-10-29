/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.domain;

import java.util.UUID;

public class MainContentId extends ContentId {
    public MainContentId() {
        super();
    }

    public MainContentId(String id) {
        super(id);
    }

    public MainContentId(UUID id) {
        super(id);
    }
}
