package orm.query.spec;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import model.EventList;
import model.QuerySpec;
import model.SearchEventList;
import orm.query.Query;
import orm.query.QueryFactory;
import orm.utils.StringUtils;

public class EventListSpec {
	public static List<Object> values = new ArrayList<Object>();

	public EventListSpec() {

	}

	public static  QuerySpec findEventList(SearchEventList search) {

		List<Query<EventList>> queries = new ArrayList<>();

		queries.add(withPlace(search.getPlace()));
		queries.add(withDay(search.getStartDate(), search.getEndDate(), "StartDate"));
		queries.add(withCreator(search.getCreator()));
		queries.add(withEventType(search.getEventType()));
		queries.add(withStatus(search.getStatus()));
		
		return new QuerySpec(QueryFactory.and(queries).condition(),values);

	}

	public static Query<EventList> withEventName(String eventName) {
		if (StringUtils.isEmpty(eventName))
			return null;

		values.add(eventName);
		return QueryFactory.eq("EventName", eventName);
	}

	public static Query<EventList> withDay(String start, String end, String columnName) {

		if (StringUtils.isEmpty(start) || StringUtils.isEmpty(end))
			return null;
		values.add(columnName);
		return QueryFactory.between(columnName, Arrays.asList(start, end));
	}

	public static Query<EventList> withCreator(String creator) {
		if (StringUtils.isEmpty(creator))
			return null;
		values.add(creator);

		return QueryFactory.eq("Creator", creator);
	}

	public static Query<EventList> withPlace(String place) {
		if (StringUtils.isEmpty(place))
			return null;

		values.add(place);
		return QueryFactory.eq("Place", place);
	}

	public static Query<EventList> withStatus(String status) {
		if (StringUtils.isEmpty(status))
			return null;
		values.add(status);
		return QueryFactory.eq("Status", status);
	}

	public static Query<EventList> withEventType(String eventType) {
		if (StringUtils.isEmpty(eventType))
			return null;
		values.add(eventType);
		return QueryFactory.eq("EventType", eventType);
	}

	public static void main(String[] args) {

		SearchEventList searchEventList = new SearchEventList();
		searchEventList.setEventName("intern");
	}

}
