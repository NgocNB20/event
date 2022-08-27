package repository.impl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.time.LocalDateTime;

import static database.Database.*;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import model.EventType;
import repository.EventTypeRepository;

@Repository
public class EventTypeRepositoryImpl implements EventTypeRepository {

	@Override
	public List<EventType> findAll() {
		String query = "SELECT * FROM MstEventType";
		try (Connection connection = getConnection();) {
			List<EventType> list = new ArrayList();
			ResultSet rs = SelectQueryString(query, new String[] {});
			while (rs.next()) {
				EventType eventType = new EventType();
				eventType.setId(rs.getInt("ID"));
				eventType.setEventName(rs.getString("EventType"));
				eventType.setIsDeleted(rs.getBoolean("IsDeleted"));
				list.add(eventType);
			}
			return list;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	@Override
	public EventType findById(Object id) {

		String query = "SELECT * FROM MstEventType WHERE ID = ?";
		try (Connection connection = getConnection();) {
			List<EventType> list = new ArrayList();
			ResultSet rs = SelectQueryString(query, new String[] { id.toString() });
			while (rs.next()) {
				EventType eventType = new EventType();
				eventType.setId(rs.getInt("ID"));
				eventType.setEventName(rs.getString("EventType"));
				eventType.setIsDeleted(rs.getBoolean("IsDeleted"));
				return eventType;
			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	public static void main(String[] args) {
		EventTypeRepository eventTypeRepository = new EventTypeRepositoryImpl();
		EventType e = eventTypeRepository.findById(1L);
		System.out.println(e.toString());
	}

}
