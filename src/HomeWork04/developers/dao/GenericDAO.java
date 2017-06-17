package HomeWork04.developers.dao;

import java.util.Collection;

public interface GenericDAO<T, ID> {

    void save(T entity);


    void update(T entity);

    T getById(ID id);

    void remov(T entity);

    Collection<T> getAll();
}
