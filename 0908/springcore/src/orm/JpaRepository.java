package orm;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import orm.paging.Page;
import orm.paging.PageAble;
import orm.query.Query;

public interface JpaRepository<T,ID extends Serializable>{
    void save(T t);
    void update(ID id,T t);
    boolean delete(ID id);
    boolean deleteAll(List<ID> ids);
    Optional findById(ID id);
    T getOne();
    Page<T> finAll(PageAble pageAble,String nameOrderBy);
    List<T> finAll();
    long count();
    Optional<T> find(Query<T> query);
    List<Object> findDistinct(String columnName);
}

