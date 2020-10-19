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
