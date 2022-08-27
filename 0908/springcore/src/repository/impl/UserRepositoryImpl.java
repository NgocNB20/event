package repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static database.Database.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import model.Department;
import model.User;
import orm.query.Query;
import orm.query.spec.UserSpec;
import repository.BaseRepository;
import repository.DepartmentRepository;
import repository.UserRepositoty;

@Repository
public class UserRepositoryImpl extends BaseRepository<User, Long> implements UserRepositoty {
	@Autowired
	DepartmentRepository departmentRepository;

	@Override
	public User checkUserNameAndPassword(User user) {
		String query = "SELECT * FROM Users WHERE username=? AND password=?";
		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setObject(1, user.getUsername());
			ps.setObject(2, user.getPassword());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				User userData = new User();
				user.setId(Integer.valueOf(rs.getObject("ID").toString()));
				user.setUsername(rs.getObject("Username").toString());
				return userData;
			}

		} catch (Exception e) {
             System.out.println(e);
             return null;
		}
		return null;
	}

	@Override
	public List<User> findAll() {
		List<User> users = new ArrayList();
		String query = "SELECT  * FROM Users IsDelete=0";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(query);) {
			ResultSet rs = ps.executeQuery();
			User user;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("Username"));
				user.setFullName(rs.getString("FullName"));
				user.setDepartID(rs.getInt("DepartID"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("Password"));
				user.setIsDeleted(rs.getBoolean("IsDelete"));
				user.setDepartName(rs.getString("DepartName"));
				users.add(user);

			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public List<User> findByDepartID(int id) {
		List<User> users = new ArrayList();
		String query = "SELECT  * FROM Users AS U INNER JOIN Department AS D ON D.ID = U.DepartID WHERE U.IsDeleted=0 AND U.DepartID=?";
		try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(query);) {
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("Username"));
				user.setFullName(rs.getString("FullName"));
				user.setDepartID(rs.getInt("DepartID"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("Password"));
				user.setDepartName(rs.getString("DepartName"));
				users.add(user);
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return users;
	}

	@Override
	public User findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByFirstDepartment(Department department) {
		String query = "SELECT  * FROM Users AS U INNER JOIN Department AS D ON D.ID = U.DepartID WHERE U.IsDeleted=0 AND U.DepartID=?";

		try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(query)) {
			ps.setObject(1, department.getId());
			ResultSet rs = ps.executeQuery();
			List<User> users = new ArrayList<User>();
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("Username"));
				user.setFullName(rs.getString("FullName"));
				user.setDepartID(rs.getInt("DepartID"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("Password"));
				user.setDepartName(rs.getString("DepartName"));
				System.out.println(department.getId());
				users.add(user);
			}
			return users;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;

	}
}
