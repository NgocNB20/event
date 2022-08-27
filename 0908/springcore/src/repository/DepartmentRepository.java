package repository;

import model.Department;
import model.EventType;
import orm.JpaRepository;

public interface DepartmentRepository  extends IBaseRepository<Department>   {
	Department getFirtDeparment();

}
