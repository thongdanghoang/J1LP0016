package model.repository;

import java.util.List;

/**
 * @author thongdanghoang
 */
public interface IRepository<K, T> {
    public void create(T t) throws Exception;

    public void edit(T t) throws Exception;

    public void destroy(K k) throws Exception;

    public T findById(K K) throws Exception;

    public List<T> findAll() throws Exception;
}
