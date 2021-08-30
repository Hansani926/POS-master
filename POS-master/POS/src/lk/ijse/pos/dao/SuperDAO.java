package lk.ijse.pos.dao;

import java.util.List;

public interface SuperDAO<T,ID> {
    boolean add(T entity)throws Exception;
   boolean update(T entity)throws Exception;
    boolean delete(ID id)throws Exception;
    T search(ID id)throws Exception;
     List<T> getAll()throws Exception;
}
