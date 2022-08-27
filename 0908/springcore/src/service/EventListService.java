package service;

import model.EventList;
import orm.paging.Page;

public interface EventListService {
	public Page<EventList> getPage(int page,int size);

}
