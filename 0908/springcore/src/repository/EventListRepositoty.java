package repository;

import model.EventAdd;
import model.EventList;
import model.PageEventList;
import model.SearchEventList;
import orm.paging.Page;

public interface EventListRepositoty extends IBaseRepository<EventList> {
	Page<EventList> findEvent(String nameOrderBy, SearchEventList search,int page, int size);
	PageEventList searchEventList(SearchEventList search,int currentPage,String userName);
	void save(EventAdd eventAdd,String url);
	EventList getEventByid(int id);
	

}
