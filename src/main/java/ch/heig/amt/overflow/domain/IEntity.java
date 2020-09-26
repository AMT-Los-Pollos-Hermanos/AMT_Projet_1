package ch.heig.amt.overflow.domain;


public interface IEntity<ENTITY, ID> {
    ID getId();
    ENTITY deepClone();
}
