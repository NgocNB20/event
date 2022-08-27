package repository;


import model.Event;
import orm.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Integer>{
	 
}
