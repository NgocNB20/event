package orm.query;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import lombok.var;

/**
 * @author <a href="mailto:ngocmeu2000@gmail.com">NgocNB20</a>
 */
public class QueryFactory {

	public QueryFactory() {
	}

	public static <T> Query<T> eq(String column, Object value) {
		return new QueryImpl<>(column, Expression::eq, value);
	}

	public static <T> Query<T> notEqual(String column, Object value) {
		return new QueryImpl<>(column, Expression::notEqual, value);
	}

	public static <T> Query<T> gt(String column, Object value) {
		return new QueryImpl<>(column, Expression::gt, value);
	}

	public static <T> Query<T> gte(String column, Object value) {
		return new QueryImpl<>(column, Expression::gte, value);
	}

	public static <T> Query<T> lt(String column, Object value) {
		return new QueryImpl<>(column, Expression::lt, value);
	}

	public static <T> Query<T> lte(String column, Object value) {
		return new QueryImpl<>(column, Expression::lte, value);
	}

	public static <T> Query<T> like(String column, Object value) {
		return new QueryImpl<>(column, Expression::like, "%" + value + "%");
	}

	public static <T> Query<T> isNull(String column, Object value) {
		return new QueryImpl<>(column, Expression::isNull, value);
	}

	public static <T> Query<T> isNotNull(String column, Object value) {
		return new QueryImpl<>(column, Expression::isNotNull, value);
	}

	public static <T> Query<T> and(List<Query<T>> queries) {

		List<Query<T>> rs = queries.stream().filter(Objects::nonNull).collect(Collectors.toList());
		if (rs.size() == 1) {
			return new QueryImpl<>(rs.get(0).condition(), rs.get(0).value());
		}
		String condition = rs.stream().map(Query::condition).collect(Collectors.joining(Expression.and()));
		List<Object> objects = rs.stream().map(Query::value).collect(Collectors.toList());

		return new QueryImpl<>(condition, objects);
	}

	public static <T> Query<T> or(List<Query<T>> queries) {

		List<Query<T>> rs = queries.stream().filter(Objects::nonNull).collect(Collectors.toList());
		if (rs.size() == 1) {
			return new QueryImpl<>(rs.get(0).condition(), rs.get(0).value());
		}
		String condition = rs.stream().map(Query::condition).collect(Collectors.joining(Expression.or()));
		List<Object> objects = rs.stream().map(Query::value).collect(Collectors.toList());

		return new QueryImpl<>(condition, objects);
	}

	 
	
	public static <T> Query<T> between(String column,List<Object> objects) {
	       String condition = " %s  BETWEEN ?  AND ? ";  
	       condition = String.format(condition, column);
	       return new QueryImpl<>(condition,objects);
		 
	}

}
