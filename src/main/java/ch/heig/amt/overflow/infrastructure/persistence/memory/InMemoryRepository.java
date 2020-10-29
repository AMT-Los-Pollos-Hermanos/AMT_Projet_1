/*
 * AMT : Project 1 - Overflow
 * Authors : Gil Balsiger, Chris Barros Henriques, Julien Béguin & Gaëtan Daubresse
 * Date : 29.10.2020
 */

package ch.heig.amt.overflow.infrastructure.persistence.memory;

import ch.heig.amt.overflow.domain.IEntity;
import ch.heig.amt.overflow.domain.IRepository;
import ch.heig.amt.overflow.domain.Id;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Deprecated
public abstract class InMemoryRepository<ENTITY extends IEntity<ENTITY, ID>, ID extends Id> implements IRepository<ENTITY, ID> {

    private final Map<ID, ENTITY> store = new ConcurrentHashMap<>();

    @Override
    public void save(ENTITY entity) {
        store.put(entity.getId(), entity);
    }

    @Override
    public void remove(ID id) {
        store.remove(id);
    }

    @Override
    public Optional<ENTITY> findById(ID id) {
        ENTITY existingEntity = store.get(id);
        if (existingEntity == null) {
            return Optional.empty();
        } else {
            return Optional.of(existingEntity.deepClone());
        }
    }

    @Override
    public Collection<ENTITY> findAll() {
        return store.values().stream().map(ENTITY::deepClone).collect(Collectors.toList());
    }

}
