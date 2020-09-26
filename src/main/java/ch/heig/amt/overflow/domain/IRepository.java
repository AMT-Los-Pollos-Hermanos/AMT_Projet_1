package ch.heig.amt.overflow.domain;

import java.util.Collection;
import java.util.Optional;

public interface IRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> {

    void save(ENTITY entity);
    void remove(ID id);
    Optional<ENTITY> findById(ID id);
    Collection<ENTITY> findAll();

}
