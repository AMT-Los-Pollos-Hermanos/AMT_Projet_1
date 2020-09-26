package ch.heig.amt.overflow.domain;

import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode
public abstract class Id {

    private final UUID id;

    public Id() {
        this.id = UUID.randomUUID();
    }

    public Id(String id) {
        this.id = UUID.fromString(id);
    }

    public Id(UUID id) {
        if(id == null) {
            throw new NullPointerException();
        }
        this.id = id;
    }

    @Override
    public String toString() {
        return id.toString();
    }
}
