package repository;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import anotation.Id;
import orm.JpaRepository;
import orm.exeption.OrmExcrption;
import orm.paging.Page;
import orm.paging.PageAble;
import orm.paging.pageImpl.PageImpl;
import orm.query.Query;
import orm.utils.StringUtils;

import static orm.utils.AnnotationUtil.*;
import static orm.utils.ReflectionUtil.*;
import static orm.utils.StringUtils.*;
import static database.Database.*;

/**
 * @author <a href="mailto:ngocmeu2000@gmail.com">NgocNB20</a>
 */
public class BaseRepository<T, ID extends Serializable> implements JpaRepository<T, ID> {

	private final Class<T> tClass;
	private final String tableName;
	private String insert;
	private String update;
	private String delete;
	private String select;
	private String distinct;
	private final String count;
//	SELECT DISTINCT Country FROM Customers;

	public BaseRepository() {
		this.tClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		this.tableName = getClassName(tClass);
		this.insert = "INSERT INTO " + tableName + " (%s) VALUES (%s)";
		this.update = "UPDATE " + tableName + " SET %s  WHERE %s";
		this.select = "SELECT * " + "FROM " + tableName;
		this.count = "SELECT COUNT(1) AS total " + "FROM " + tableName;
		this.delete = "DELETE FROM " + tableName;
		this.distinct = "SELECT DISTINCT (%s)" + "FROM " + tableName;
	}

	@Override
	public void save(T t) {
		Field[] fields = tClass.getDeclaredFields();
		final StringBuilder columns = new StringBuilder();
		final StringBuilder values = new StringBuilder();
		Map<String, Integer> map = new HashMap<>();
		AtomicInteger index = new AtomicInteger(1);

		Arrays.stream(fields).forEach(field -> {
			if (field.isAnnotationPresent(Id.class)) {
				boolean isAutoincrement = isAutoIncrement(tClass, field.getName());
				if (!isAutoincrement) {
					String primaryName = primaryColumn(tClass, field.getName());
					columns.append(primaryName).append(",");
					values.append("?,");
					map.put(field.getName(), index.getAndIncrement());
				}
			} else {
				String columnName = getColumnName(tClass, field.getName());
				columns.append(columnName).append(",");
				values.append("?,");
				map.put(field.getName(), index.getAndIncrement());
			}
		});

		String column = columns.deleteCharAt(columns.length() - 1).toString();
		String value = values.deleteCharAt(values.length() - 1).toString();

		insert = String.format(insert, column, value);

		Connection connection = null;

		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement ps = connection.prepareStatement(insert);
			connection.setAutoCommit(false);

			Arrays.stream(fields).forEach(field -> {
				if (field.isAnnotationPresent(Id.class)) {
					boolean isAutoincrement = isAutoIncrement(tClass, field.getName());
					if (!isAutoincrement) {
						try {
							ps.setObject(map.get(field.getName()), get(t, field));
						} catch (SQLException exception) {
							throw new OrmExcrption(exception.getMessage());
						}
					}
				} else {
					try {
						ps.setObject(map.get(field.getName()), get(t, field));
					} catch (SQLException exception) {
						throw new OrmExcrption(exception.getMessage());
					}
				}
			});

			ps.executeUpdate();
			connection.commit();

		} catch (SQLException | ClassNotFoundException exception) {
			if (connection == null) {
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			throw new OrmExcrption(exception.getMessage());
		} finally {
			if (connection == null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					throw new OrmExcrption(exception.getMessage());
				}
			}
		}
	}

	@Override
	public void update(ID id, T t) {
		StringBuilder set = new StringBuilder();
		StringBuilder condition = new StringBuilder();
		Field[] fields = t.getClass().getDeclaredFields();
		Map<String, Integer> map = new HashMap<>();
		AtomicInteger index = new AtomicInteger(1);
		AtomicReference<String> primary = new AtomicReference<>();

		Arrays.stream(fields).forEach(field -> {
			if (!field.isAnnotationPresent(Id.class)) {
				String columnName = getColumnName(tClass, field.getName());
				if (isEmpty(columnName)) {
					throw new OrmExcrption("PrimaryName is null");
				}
				set.append(columnName).append(" = ?,");
				map.put(columnName, index.getAndIncrement());
			} else {
				String primaryName = primaryColumn(tClass, field.getName());
				if (isEmpty(primaryName)) {
					throw new OrmExcrption("primaryName is null");
				}
				condition.append(primaryName).append(" = ?");
				primary.set(field.getName());
			}
		});
		map.put(primary.get(), index.getAndIncrement());
		set.deleteCharAt(set.length() - 1);
		this.update = String.format(this.update, set, condition);

		System.out.println(this.update);

		Arrays.stream(fields).forEach(field -> {

			System.out.println(get(t, field));

		});
		Connection connection = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			final PreparedStatement ps = connection.prepareStatement(update);
			connection.setAutoCommit(false);

			Arrays.stream(fields).forEach(field -> {
				try {
					System.out.println(get(t, field));
					ps.setObject(map.get(field.getName()), get(t, field));
				} catch (SQLException exception) {
					throw new OrmExcrption(exception.getMessage());
				}
			});

			ps.executeUpdate();
			connection.commit();

		} catch (SQLException | ClassNotFoundException exception) {
			if (connection == null) {
				try {
					connection.rollback();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			throw new OrmExcrption(exception.getMessage());
		} finally {
			if (connection == null) {
				try {
					connection.close();
				} catch (SQLException exception) {
					throw new OrmExcrption(exception.getMessage());
				}
			}
		}

	}

	@Override
	public Optional<T> findById(ID id) {
		Optional<Field> field = Arrays.stream(tClass.getDeclaredFields())
				.filter(fieldOne -> fieldOne.isAnnotationPresent(Id.class)).findFirst();
		if (!field.isPresent()) {
			return Optional.empty();
		}
		this.select = this.select + " WHERE " + primaryColumn(tClass, field.get().getName()) + " =? ";
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(this.select);
			ps.setObject(1, id);
			ResultSet resultSet = ps.executeQuery();
			if (resultSet.next()) {
				return Optional.of(mapToEntity(resultSet, tClass));
			}
		} catch (Exception e) {
			throw new OrmExcrption(e.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public T getOne() {
		return null;
	}

	/*
	 * SELECT * FROM Users ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY
	 * ps.setInt(1, (currentPage - 1) * pageSize); ps.setInt(2, pageSize);
	 */

	@Override
	public Page<T> finAll(PageAble pageAble, String nameOrderBy) {
		String selectLimit;
		if (pageAble == null) {
			selectLimit = this.select;
		} else {
			 
			selectLimit = this.select + " ORDER BY " + nameOrderBy + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
		}
		int page = pageAble == null ? 0 : pageAble.getPage();
		int size = pageAble == null ? 0 : pageAble.getSize();
		List<T> data = getAll(selectLimit, pageAble);
		long totalItem = count();
		Page<T> pageData = PageImpl.of(size, page, totalItem, data);

		return pageData;
	}

	@Override
	public List<T> finAll() {
		List<T> list = getAll(this.select, null);
		return list;
	}

	@Override
	public long count() {
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(this.count);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				return rs.getLong("total");

			}

		} catch (SQLException | ClassNotFoundException exception) {
			throw new OrmExcrption(exception.getMessage());
		}
		return 0;
	}

	private List<T> getAll(String sql, PageAble pageAble) {
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sql);
			System.out.println(sql);
			if (pageAble != null) {
				ps.setInt(1, pageAble.getOffSet());
				ps.setInt(2, pageAble.getSize());
			}

			ResultSet rs = ps.executeQuery();
			List<T> list = new ArrayList<>();
			while (rs.next()) {
				T t = mapToEntity(rs, tClass);
				list.add(t);
			}
			return list;
		} catch (SQLException | InvocationTargetException | NoSuchMethodException | InstantiationException
				| IllegalAccessException | ClassNotFoundException exception) {
			throw new OrmExcrption(exception.getMessage());
		}

	}

	@Override
	public boolean delete(ID id) {
		Field[] fields = tClass.getDeclaredFields();
		Optional<Field> fieldId = Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Id.class))
				.findFirst();
		if (fieldId.isPresent()) {
			String columnName = primaryColumn(tClass, fieldId.get().getName());
			this.delete = this.delete + " WHERE " + columnName + " = ?";
			Connection con = null;
			try {
				con = getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement(this.delete);
				ps.setObject(1, id);
				ps.executeUpdate();
				con.commit();

				return true;
			} catch (SQLException | ClassNotFoundException exception) {
				try {
					if (con != null) {
						con.rollback();
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				exception.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				}
			}
			return false;

		}
		System.out.println("fail2");
		return false;
	}

	@Override
	public boolean deleteAll(List<ID> ids) {
		String idString = ids.stream().map(Object::toString).collect(Collectors.joining(","));
		Field[] fields = tClass.getDeclaredFields();
		Optional<Field> fieldId = Arrays.stream(fields).filter(field -> field.isAnnotationPresent(Id.class))
				.findFirst();
		if (fieldId.isPresent()) {
			String columnName = primaryColumn(tClass, fieldId.get().getName());
			this.delete = this.delete + " WHERE " + columnName + " IN (" + idString + ")";
			System.out.println(this.delete);
			Connection con = null;
			try {
				con = getConnection();
				con.setAutoCommit(false);
				PreparedStatement ps = con.prepareStatement(this.delete);
//                int index = 1;
//                for (ID id : ids) {
//                    ps.setObject(++index, id);
//                }
				ps.executeUpdate();
				con.commit();
				return true;
			} catch (SQLException | ClassNotFoundException exception) {
				try {
					if (con != null) {
						con.rollback();
					}
				} catch (SQLException throwables) {
					throwables.printStackTrace();
				}
				exception.printStackTrace();
			} finally {
				if (con != null) {
					try {
						con.close();
					} catch (SQLException exception) {
						exception.printStackTrace();
					}
				}
			}
			return true;

		}

		return false;
	}

	@Override
	public Optional<T> find(Query<T> query) {
		StringBuilder sqlBuilder = new StringBuilder(this.select);
		if (StringUtils.isEmpty(query.condition())) {
			return Optional.empty();
		}
		sqlBuilder.append(" WHERE ").append(query.condition());
		System.out.println(sqlBuilder);
		try (Connection connection = getConnection()) {
			PreparedStatement ps = connection.prepareStatement(sqlBuilder.toString());
			if (query.value() != null) {
				ps.setObject(1, query.value());
			} else if (query.values() != null) {
				int j = 1;
				for (Object value : query.values()) {
					ps.setObject(j++, value);
				}
			}
			ResultSet rs = ps.executeQuery();
			T t = null;
			if (rs.next()) {
				t = mapToEntity(rs, tClass);
				return Optional.of(t);
			}
		} catch (SQLException | NoSuchMethodException | InvocationTargetException | InstantiationException
				| IllegalAccessException | ClassNotFoundException exception) {
			throw new OrmExcrption(exception.getMessage());
		}
		return Optional.empty();
	}

	@Override
	public List<Object> findDistinct(String columnName) {
		distinct = String.format(distinct, columnName);
		List<Object> list = new ArrayList<Object>();
		String value = "";
		System.out.println("diss" + distinct);
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(distinct);) {
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {			 
				list.add(rs.getObject(columnName));
			}
			return list;
		} catch (SQLException | ClassNotFoundException exception) {
			throw new OrmExcrption(exception.getMessage());
		}
	}

}
