package by.exposit.delivery.api.dao;

import by.exposit.delivery.entities.AEntity;

import java.util.List;
import java.util.Optional;

public interface  IAGenericDao<T extends AEntity<K>, K extends Number> {

    Class<T> getGenericClass();
    List<T> getAll();
    Optional<T> get(K id);
    void delete(K id);
    Optional<T> create(T entity);
    Optional<T> update(K id, T entity);
    K getLastKey();
}
