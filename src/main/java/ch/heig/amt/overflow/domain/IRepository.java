package ch.heig.amt.overflow.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<IEntity, Id> {

    void save(IEntity entity);
    void remove(Id id);
    Optional<IEntity> findById(Id id);
    Collection<IEntity> findAll();

}
