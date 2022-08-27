package repository.impl;

import org.springframework.stereotype.Repository;

import model.Event;
import repository.BaseRepository;
import repository.EventRepository;

@Repository
public class EventRepositoryImpl extends BaseRepository<Event, Integer> implements EventRepository {

}
