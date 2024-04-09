package org.api.rest.dao.interfacee;

import javax.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public interface ParentDAO<T> {

    void save(T t, EntityManager em);
    void  update(T t, EntityManager em);
    void delete(T t, EntityManager em);

    List<T> getAll(EntityManager em);
}
