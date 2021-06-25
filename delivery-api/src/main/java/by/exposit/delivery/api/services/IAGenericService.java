package by.exposit.delivery.api.services;

import java.util.List;

public interface IAGenericService<T, K extends Number> {

    List<T> findAll();
    T findById(K id);
    void delete(K id);
    T create(T entity);
    T update(K id, T entity);
}
