package lodz.p.pk.dao;

public interface Dao<T> extends AutoCloseable{

    T read();

    void write(T obj);
}
