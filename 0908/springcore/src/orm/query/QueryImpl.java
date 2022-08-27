package orm.query;

import java.util.List;

/**
 * @author <a href="mailto:ngocmeu2000@gmail.com">NgocNB20</a>
 */
public class QueryImpl<T> implements Query<T> {
	private String condition;
	private Object value;
	private List<Object> values;

	public QueryImpl(String condition, Expression expression, Object value) {
		this.condition = condition + expression.expression() + "?";
		this.value = value;
	}

	public QueryImpl(String condition, Object value, Expression expression) {
		this.condition = condition + expression.expression() + " " + value + " ";
		this.value = null;
	}

	public QueryImpl(String condition, List<Object> values) {
		this.condition = condition;
		this.values = values;
	}

	public QueryImpl(String condition, Object value) {
		this.condition = condition;
		this.value = value;
	}

	@Override
	public String condition() {
		return this.condition;
	}

	@Override
	public Object value() {
		return this.value;
	}

	@Override
	public List<Object> values() {
		return this.values;
	}
}
