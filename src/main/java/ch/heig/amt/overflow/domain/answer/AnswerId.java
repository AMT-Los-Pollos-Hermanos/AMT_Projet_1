package ch.heig.amt.overflow.domain.answer;

import ch.heig.amt.overflow.domain.MainContentId;

import java.util.UUID;

public class AnswerId extends MainContentId {
    public AnswerId() {
        super();
    }

    public AnswerId(String id) {
        super(id);
    }

    public AnswerId(UUID id) {
        super(id);
    }
}
