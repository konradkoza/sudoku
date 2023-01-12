package lodz.p.pk.dao;

import lodz.p.pk.exceptions.ReadDaoException;
import lodz.p.pk.exceptions.WriteDaoException;

public interface Dao<T> extends AutoCloseable {

    T read() throws ReadDaoException;

    void write(T obj) throws WriteDaoException;
}
