package orm.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import anotation.Id;
import orm.exeption.OrmExcrption;
import static orm.utils.AnnotationUtil.*;

/**
  
 */

/* build get set */
public class ReflectionUtil {
	private ReflectionUtil() {
	}

	public static <T> Object get(Object instance, Field field) {
		String name = field.getName();
		String prefix;
		if (field.getType().equals(Boolean.class) || field.getType().equals(boolean.class)) {
			prefix = "Is";
		} else {
			prefix = "get";
		}
		name = prefix + name.substring(0, 1).toUpperCase() + name.substring(1);

		try {
			Method method = instance.getClass().getMethod(name);
			return method.invoke(instance);
		} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			return null;
		}
	}

	public static <T> void set(Object instance, Field field, Object value) {
		String name = field.getName();
		String prefix = "set";
		name = prefix + name.substring(0, 1).toUpperCase() + name.substring(1);
		try {
			Method method = instance.getClass().getDeclaredMethod(name, field.getType());
			if (field.getType().equals(LocalDate.class)) {
				method.invoke(instance, LocalDate.parse(value.toString()));
			} else if (field.getType().equals(LocalTime.class)) {
				method.invoke(instance, LocalTime.parse(value.toString()));
			} else if (field.getType().equals(Boolean.class)) {
				method.invoke(instance, new Boolean(true));
			} else {
				method.invoke(instance, value);
			}
		} catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
			throw new OrmExcrption(e.getMessage());
		}
	}

	public static <T> T mapToEntity(ResultSet resultSet, Class<T> tClass)
			throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		final T t = tClass.getDeclaredConstructor().newInstance();
		Field[] fields = tClass.getDeclaredFields();
		Arrays.stream(fields).forEach(field -> {
			String columnName;
			if (!field.isAnnotationPresent(Id.class)) {
				columnName = getColumnName(tClass, field.getName());
			} else {
				columnName = primaryColumn(tClass, field.getName());
			}
			try {
				set(t, field, resultSet.getObject(columnName));

			} catch (SQLException exception) {
				throw new OrmExcrption(exception.getMessage());
			}

		});
		return t;
	}
}
