package HomeWork04.developers.dao;

import java.io.IOException;
import java.util.Collection;

public interface GenericDAO<T, ID> {

    void save(T entity);

    void update(T entity);

    T getById(ID id);

    boolean remov(ID id) throws Exception;

    Collection<T> getAll();
}
