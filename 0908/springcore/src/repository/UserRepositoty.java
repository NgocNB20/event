package repository;

import java.util.List;

import model.Department;
import model.User;
import orm.JpaRepository;

public interface UserRepositoty extends IBaseRepository<User>{
	User checkUserNameAndPassword(User user);
	List<User> findByDepartID(int id);
	List<User> findByFirstDepartment(Department department);
	
}
