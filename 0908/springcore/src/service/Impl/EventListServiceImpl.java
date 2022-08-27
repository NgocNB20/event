package service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.EventList;
import orm.paging.Page;
import orm.paging.PageAble;
import orm.paging.PageRequest;
import repository.EventListRepositoty;
import service.EventListService;

@Service
public class EventListServiceImpl implements EventListService {

	@Autowired
	EventListRepositoty EventListRp;

	 

	public static void main(String[] args) {
		EventListService eventListService = new EventListServiceImpl();
		 
	}



	@Override
	public Page<EventList> getPage(int page, int size) {
		// TODO Auto-generated method stub
		return null;
	}

}
