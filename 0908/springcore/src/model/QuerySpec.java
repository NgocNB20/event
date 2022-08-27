package model;

import java.util.List;

public class QuerySpec {
	private String query;
	List<Object> values;
	
	
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public List<Object> getValues() {
		return values;
	}
	public void setValues(List<Object> values) {
		this.values = values;
	}
	public QuerySpec(String query, List<Object> values) {
 
		this.query = query;
		this.values = values;
	}
	public QuerySpec() {
		 
	}

	
	
}
