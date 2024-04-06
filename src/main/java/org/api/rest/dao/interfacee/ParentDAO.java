package org.api.rest.dao.interfacee;

import java.util.List;

public interface ParentDAO<T> {

    void add(T entity);

    void delete(Integer id);

    void update(T dtoModel);

    T get(Integer id);

    List<T> getAll();
}
