package com.acme.university.repository;

import java.util.List;
import java.util.Optional;

public interface CRUDRepository<T, ID> {
    void create(T t) throws Exception;

    List<T> findAll() throws Exception;

    Optional<T> findByID(ID id) throws Exception;

    boolean update(T t) throws Exception;

    boolean delete(T t) throws Exception;
}
