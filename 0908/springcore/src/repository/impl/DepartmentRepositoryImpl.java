package repository.impl;

import static database.Database.SelectQueryString;
import static database.Database.getConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.Department;
import model.EventList;
import model.EventType;
import repository.BaseRepository;
import repository.DepartmentRepository;
import repository.EventListRepositoty;
import repository.EventTypeRepository;

@Repository
public class DepartmentRepositoryImpl  implements DepartmentRepository {

	@Override
	public List<Department> findAll() {
		String query = "SELECT * FROM Department";
		try (Connection connection = getConnection();) {
			List<Department> list = new ArrayList();
			ResultSet rs = SelectQueryString(query, new String[] {});
			while (rs.next()) {
				System.out.println("123");
				Department department = new Department();
				department.setId(rs.getInt("ID"));
				department.setDepartName(rs.getString("DepartName"));
				department.setIsDeleted(rs.getBoolean("IsDeleted"));
				list.add(department);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public Department findById(Object id) {

		String query = "SELECT * FROM Department WHERE ID = ?";
		try (Connection connection = getConnection();) {
			ResultSet rs = SelectQueryString(query, new String[] { id.toString() });
			while (rs.next()) {
				System.out.println("123");
				Department department = new Department();
				department.setId(rs.getInt("ID"));
				department.setDepartName(rs.getString("DepartName"));
				department.setIsDeleted(rs.getBoolean("IsDeleted"));	
				return department;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
}
	public static void main(String[] args) {
		DepartmentRepositoryImpl DepartmentRepositoryImpl = new DepartmentRepositoryImpl();
		List<Department> e = DepartmentRepositoryImpl.findAll();
		e.forEach(ca->{
			System.out.println(ca.toString());
		});
	}

	@Override
	public Department getFirtDeparment() {
		String query = "SELECT * FROM Department";
		try (Connection connection = getConnection();) {
			ResultSet rs = SelectQueryString(query, new String[] {});
			while (rs.next()) {
				Department department = new Department();
				department.setId(rs.getInt("ID"));
				department.setDepartName(rs.getString("DepartName"));
				department.setIsDeleted(rs.getBoolean("IsDeleted"));
			    return department;
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		 
		return null;
	}
}
