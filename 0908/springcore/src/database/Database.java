package database;

import static orm.utils.AnnotationUtil.getColumnName;
import static orm.utils.AnnotationUtil.primaryColumn;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import anotation.Id;
import orm.exeption.OrmExcrption;

public class Database {

	final static String URL_SQLSEVER = "jdbc:sqlserver://localhost:1433;databaseName=Managment;useLOBs=false";
	final static String USER_NAME_SQLSEVER = "sa";
	final static String PASSWORD_SQLSEVER = "12345678";
	final static String DRIVER_SQLSEVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName(DRIVER_SQLSEVER);
		return DriverManager.getConnection(Database.URL_SQLSEVER, Database.USER_NAME_SQLSEVER,
				Database.PASSWORD_SQLSEVER);
	}
	/**
	 * Method : Close connect DB
	 */
	public static void CloseConnection(ResultSet rs, PreparedStatement ptsmt, Connection conn) throws SQLException {

		if (rs != null) {
			rs.close();
		}

		if (ptsmt != null) {
			ptsmt.close();
		}

		if (conn != null) {
			conn.close();
		}
	}

	/**
	 * Method thuc thi cau query SELECT
	 * @throws ClassNotFoundException 
	 * 
	 */
	public static ResultSet SelectQueryString(String query, Object[] parameter) throws SQLException, ClassNotFoundException {

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (parameter.length > 0) {
			int index = 1;
			for (Object param : parameter) {
				pstmt.setObject(index, param);
				index++;
			}
		}

		ResultSet rs = pstmt.executeQuery();
		return rs;
	}

	/**
	 * Method thuc thi cau query khac SELECT
	 * @throws ClassNotFoundException 
	 * 
	 */
	public static int ModifyQueryString(String query, Object[] parameter) throws SQLException, ClassNotFoundException {

		Connection conn = getConnection();
		PreparedStatement pstmt = conn.prepareStatement(query);
		if (parameter.length > 0) {
			int index = 1;
			for (Object param : parameter) {
				pstmt.setObject(index, param);
				index++;
			}
		}

		int count = pstmt.executeUpdate();
		return count;
	}
	
	public static List<String> mapToEntity(Class tClass)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		 
		Field[] fields = tClass.getDeclaredFields();
		List<String> columns = new ArrayList();
		Arrays.stream(fields).forEach(field -> {
			String columnName;
			if (!field.isAnnotationPresent(Id.class)) {
				columns.add(getColumnName(tClass, field.getName()));
			} else {
				columns.add(getColumnName(tClass, field.getName()));
			}
		});
		return columns;
	}

}