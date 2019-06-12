package pl.lodz.p.pkck.dao;

import pl.lodz.p.pkck.exception.DaoException;

public interface Dao<T> {
    public T read(String path) throws DaoException;

    public void write(T obj, String path) throws DaoException;
}
