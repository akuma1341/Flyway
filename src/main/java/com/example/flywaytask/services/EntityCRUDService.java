package com.example.flywaytask.services;

import java.util.List;

public interface EntityCRUDService<E, ID> {

    List<E> getAll();

    E getById(ID id);

    E save(E entity);

    List<E> saveAll(Iterable<E> entities);

    void delete(ID id);
}
