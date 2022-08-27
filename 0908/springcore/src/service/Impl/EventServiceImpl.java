package service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Event;
import repository.BaseRepository;
import repository.EventRepository;
import service.EventService;

@Service
public class EventServiceImpl implements EventService  {

	
	@Autowired
	EventRepository eventRepository;

	@Override
	public List<Object> getAllDataColumn(String columnName) {
		return eventRepository.findDistinct(columnName);
		 
	}
	

}
