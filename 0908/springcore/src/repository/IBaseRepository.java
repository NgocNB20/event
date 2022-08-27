package repository;

import java.util.List;

public interface IBaseRepository<T> {
	List<T> findAll();
	T findById(Object id);
	 
	 
}
