package repository.impl;

import static database.Database.SelectQueryString;
import static utils.Util.checkEmptyNull;
import static utils.Util.convertTime;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import constant.ConstantData;
import database.Database;
import model.EventAdd;
import model.EventList;
import model.PageEventList;
import model.SearchEventList;
import model.User;
import model.VoteOption;
import orm.paging.Page;
import repository.EventListRepositoty;

@Repository
public class EventListRepositotyImpl implements EventListRepositoty {
	public static final int PageSize = 1;

	private List<EventList> getByQuery(String query, List<Object> values) throws ClassNotFoundException, SQLException {
		String[] arr = values.stream().toArray(String[]::new);
		ResultSet rs = SelectQueryString(query, arr);
		while (rs.next()) {
			EventList eventList = new EventList();
			eventList.setId(rs.getInt("ID"));
			eventList.setEventName(rs.getString("EventName"));
			eventList.setDescription(rs.getString("Description"));
			eventList.setTime(rs.getString("Time"));
			eventList.setPlace(rs.getString("Place"));
			eventList.setCreatetor(rs.getString("Creator"));
			eventList.setStatus(rs.getBoolean("Status"));
			eventList.setJoined(rs.getInt("Joined"));
			eventList.setNotJoined(rs.getInt("NotJoined"));
			eventList.setStartDate(LocalDate.parse(rs.getString("StartDate")));
			eventList.setStartHour(LocalTime.parse(rs.getString("StartHour")));
			eventList.setEndDate(LocalDate.parse(rs.getString("EndDate")));
			eventList.setEndHour(LocalTime.parse(rs.getString("EndHour")));
		}
		return null;
	}

	@Override
	public Page<EventList> findEvent(String nameOrderBy, SearchEventList search, int page, int size) {

		return null;
	}

	@Override
	public PageEventList searchEventList(SearchEventList search, int currentPage, String userName) {
		List<Object> values = new ArrayList();
		StringBuilder query = new StringBuilder();

		List<EventList> eventLists = new ArrayList();
		query.append(" SELECT * FROM Eventlist WHERE 1=1 ");

		StringBuilder condition = new StringBuilder();
		if (search != null) {
			if (!checkEmptyNull(search.getEventName())) {
				condition.append(" AND (EventName LIKE ? OR Description LIKE ? ) ");
				values.add("%" + search.getEventName() + "%");
				values.add("%" + search.getEventName() + "%");
			}

			if (!checkEmptyNull(search.getPlace())) {
				condition.append(" AND Place LIKE ? ");
				values.add("%" + search.getPlace() + "%");
			}

			if (!checkEmptyNull(search.getEventType())) {
				condition.append(" AND EventTypeID = ? ");
				values.add(search.getEventType());
			}

			if (!checkEmptyNull(search.getStatus())) {
				condition.append(" AND Status = ? ");
				values.add(search.getStatus());
			}

			if (!checkEmptyNull(search.getStartDate())) {
				condition.append(" AND StartDate <= ? ");
				values.add(search.getStartDate());
			}
			if (!checkEmptyNull(search.getEndDate())) {
				condition.append(" AND EndDate >= ? ");
				values.add(search.getEndDate());
			}
			if (!checkEmptyNull(search.getStartTime())) {
				condition.append(" AND StartHour <= ? ");
				values.add(search.getStartTime() + ":00:00");
				System.out.println(search.getStartTime() + ":00:00");
			}
			if (!checkEmptyNull(search.getEndTime())) {
				condition.append(" AND EndHour >= ? ");
				values.add(search.getEndTime() + ":00:00");
				System.out.println(search.getEndTime() + ":00:00");
			}

			if (!checkEmptyNull(search.getDepartID())) {
				System.out.println("DepartID" + search.getDepartID());
				condition.append(" AND DepartID = ? ");
				values.add(search.getDepartID());
			}
		}

		System.out.println(condition.toString());
		query.append(condition);
		System.out.println(query.toString());
		int totalItem = count(query.toString(), values);

		int totalPage = getPage(totalItem, PageSize);

		query.append(" ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY");
		System.out.println(query.toString());
		values.add((currentPage) * PageSize);
		values.add(PageSize);

		try {
			ResultSet rs = SelectQueryString(query.toString(), values.stream().toArray(Object[]::new));
			while (rs.next()) {
				EventList eventList = new EventList();
				eventList.setId(rs.getInt("ID"));
				eventList.setEventName(rs.getString("EventName"));
				eventList.setDescription(rs.getString("Description"));
				eventList.setTime(rs.getString("Time"));
				eventList.setPlace(rs.getString("Place"));
				eventList.setCreatetor(rs.getString("Creator"));
				eventList.setStatus(rs.getBoolean("Status"));
				eventList.setJoined(rs.getInt("Joined"));
				eventList.setNotJoined(rs.getInt("NotJoined"));
				eventList.setDepartment(rs.getString("DepartName"));
				eventList.setStartDate(LocalDate.parse(rs.getString("StartDate")));
				eventList.setStartHour(LocalTime.parse(rs.getString("StartHour")));
				eventList.setEndDate(LocalDate.parse(rs.getString("EndDate")));
				eventList.setEndHour(LocalTime.parse(rs.getString("EndHour")));
				if (userName.equals(rs.getString("Creator")))
				{
					System.out.println("TRUE");
					eventList.setStatusDelete(true);
				}else {
					System.out.println("FALSE");
					eventList.setStatusDelete(false);
				}
				eventLists.add(eventList);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}

		return new PageEventList(PageSize, totalPage, eventLists, currentPage);
	}

	private int count(String sql, List<Object> values) {
		Object[] arrObj = values.stream().toArray(Object[]::new);

		System.out.println("query count" + sql);
		int i = 0;
		try {
			ResultSet rs = SelectQueryString(sql, arrObj);
			while (rs.next()) {
				i++;
			}
			return i;

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return i;

	}

	private int getPage(int totalItem, int pageSize) {
		if (totalItem % pageSize == 0) {
			return totalItem / pageSize;
		} else {
			return (totalItem / pageSize) + 1;
		}

	}

	@Override
	public List<EventList> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EventList findById(Object id) {
		// TODO Auto-generated method stub
		return null;
	}
	/*
	 * public static List<EventList> testSearch(SearchEventList search) throws
	 * ClassNotFoundException, SQLException {
	 * 
	 * }
	 */

	@Override
	public void save(EventAdd eventAdd,String url) {
		
		String queryEvent = "INSERT INTO Events(EventName,EventType,EventImage,Creator,Status,OptionType,StartDate,StartHour,EndDate,EndHour,Description,Place) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
 
		if (eventAdd.getOptionType().equals("1")) {
			try (Connection connection = Database.getConnection();) {

				connection.setAutoCommit(false);

				if (!eventAdd.getImageMain().isEmpty()) {
					File file = new File(url, eventAdd.getImageMain().getOriginalFilename());
					eventAdd.getImageMain().transferTo(file);

				}
				try (PreparedStatement psEvent = connection.prepareStatement(queryEvent)) {

					psEvent.setObject(1, eventAdd.getEventName());
					psEvent.setObject(2, eventAdd.getEventType());
					psEvent.setObject(3, eventAdd.getImageMain().getOriginalFilename());
					psEvent.setObject(4, eventAdd.getCreator());
					psEvent.setObject(5, 1);
					psEvent.setObject(6, eventAdd.getOptionType());
					psEvent.setObject(7, LocalDate.parse(eventAdd.getStartDate()));
					psEvent.setObject(8, convertTime(eventAdd.getStartHour(), eventAdd.getStartMinute()));
					psEvent.setObject(9, LocalDate.parse(eventAdd.getEndDate()));
					psEvent.setObject(10, convertTime(eventAdd.getEndHour(), eventAdd.getEndMinute()));
					psEvent.setObject(11, eventAdd.getDescription());
					psEvent.setObject(12, eventAdd.getPlace());
					psEvent.executeUpdate();
				    saveVoteOptions(eventAdd,connection);
				} catch (Exception e) {
					e.printStackTrace();
					connection.rollback();				 
				}
				connection.commit();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
 
		} else {

			try (Connection connection = Database.getConnection();) {

				connection.setAutoCommit(false);

				if (eventAdd.getImageMain() != null && !eventAdd.getImageMain().isEmpty()) {
					File file = new File(ConstantData.URL_IMAGE, eventAdd.getImageMain().getOriginalFilename());
					eventAdd.getImageMain().transferTo(file);

				}
				try (PreparedStatement psEvent = connection.prepareStatement(queryEvent)) {

					psEvent.setObject(1, eventAdd.getEventName());
					psEvent.setObject(2, eventAdd.getEventType());
					if (eventAdd.getImageMain() != null && !eventAdd.getImageMain().isEmpty()) {
						psEvent.setObject(3, eventAdd.getImageMain().getOriginalFilename());
					} else {
						psEvent.setObject(3, null);
					}
					psEvent.setObject(4, eventAdd.getCreator());
					psEvent.setObject(5, 0);
					psEvent.setObject(6, eventAdd.getOptionType());
					psEvent.setObject(7, LocalDate.parse(eventAdd.getStartDate()));
					psEvent.setObject(8, convertTime(eventAdd.getStartHour(), eventAdd.getStartMinute()));
					psEvent.setObject(9, LocalDate.parse(eventAdd.getEndDate()));
					psEvent.setObject(10, convertTime(eventAdd.getEndHour(), eventAdd.getEndMinute()));
					psEvent.setObject(11, eventAdd.getDescription());
					psEvent.setObject(12, eventAdd.getPlace());
					psEvent.executeUpdate();
				    saveVoteOptions(eventAdd,connection);
				} catch (Exception e) {
					e.printStackTrace();
					connection.rollback();					 
				}
				connection.commit();
				connection.setAutoCommit(true);
			} catch (Exception e) {
				System.out.println(e);
				e.printStackTrace();
			}
			 

		}

	}

	private void saveRegisterEvent(EventAdd eventAdd, Connection connection)
			throws SQLException {
		List<User> userRegisters = getUserRegister(eventAdd);
		String queryRegister = "INSERT INTO RegistEvents(Username,EventID,IsJoined,Voted,AttachedPersonAdult,AttachedPersonChild,VoteOptionID) VALUES(?,?,?,?,?,?,?)";
		PreparedStatement psRegister=connection.prepareStatement(queryRegister);
		Integer eventID = lastIDEvent();

		for (User user : userRegisters) {
			psRegister.setObject(1, user.getUsername());
			psRegister.setObject(2, eventID);
			psRegister.setObject(3, 0);
			psRegister.setObject(4, 0);
			psRegister.setObject(5, 0);
			psRegister.setObject(6, 0);
			psRegister.setObject(7, eventAdd.getVoteOptionId());
			psRegister.executeUpdate();
		}

	}

	private void saveVoteOptions(EventAdd eventAdd,Connection connection) throws SQLException {
		List<VoteOption> voteOptions = getVoteOption(eventAdd);
		String queryVoteOption = "INSERT INTO VoteOption(EventID,StartDate,Place,OptionImage) VALUES(?,?,?,?)";
		
				PreparedStatement psVoteOption= connection.prepareStatement(queryVoteOption, Statement.RETURN_GENERATED_KEYS);
				if (voteOptions != null) {
					for (VoteOption voteOption : voteOptions) {
						psVoteOption.setObject(1, voteOption.getEventId());
						if (voteOption.getStartDate() != null) {
							psVoteOption.setObject(2, LocalDate.parse(voteOption.getStartDate()));
						} else {
							psVoteOption.setObject(2, null);
						}
						psVoteOption.setObject(3, voteOption.getPlace());
						psVoteOption.setObject(4, voteOption.getOptionImage());
						psVoteOption.executeUpdate();
						
						
						ResultSet rs = psVoteOption.getGeneratedKeys();
						while(rs.next()) {
							eventAdd.setVoteOptionId(rs.getObject(1).toString());
						}	
						saveRegisterEvent(eventAdd, connection);
					}
				}else {
					   saveRegisterEvent(eventAdd, connection);
				}

			 

 
			for (VoteOption voteOption : voteOptions) {

				try {
					if (voteOption.getImageMuFile() != null && voteOption.getImageMuFile().getOriginalFilename() != null
							&& !voteOption.getImageMuFile().getOriginalFilename().isEmpty()
							&& voteOption.getImageMuFile() != null) {
						File file = new File(ConstantData.URL_IMAGE, voteOption.getImageMuFile().getOriginalFilename());
						voteOption.getImageMuFile().transferTo(file);
					}
				} catch (IllegalStateException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		 

	}

	private List<User> getUserRegister(EventAdd eventAdd) {
		String query = "  SELECT * FROM  Users AS U INNER JOIN Department AS D ON U.DepartID=D.ID where D.ID IN (%s) OR U.ID IN (%s) ";
		List<User> users = new ArrayList<User>();
		try {
			ResultSet rs = SelectQueryString(
					String.format(query, eventAdd.getDataDepartments(), eventAdd.getDataMembers()), new String[] {});
			User user;
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("ID"));
				user.setUsername(rs.getString("Username"));
				users.add(user);
			}
			return users;
		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		return null;

	}

	private int lastIDEvent() {
		String query = "SELECT TOP 1 * FROM [Events] ORDER BY ID DESC";
		try (ResultSet rs = SelectQueryString(query, new String[] {})) {
			if (rs.next()) {
				return Integer.valueOf(rs.getObject("ID").toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	private List<VoteOption> getVoteOption(EventAdd eventAdd) {
		String[] places = eventAdd.getPlaceVoteOption();
		String[] dates = eventAdd.getDateVoteOption();
 
		MultipartFile[] multipartFiles = eventAdd.getImageVoteOption();
		Integer eventID = lastIDEvent();

		List<VoteOption> voteOptions = new ArrayList();
		VoteOption voteOption;

		if (dates == null && places == null) {
			return voteOptions;
		}

		if (places != null) {
			int i = 0;
			for (String place : places) {
				voteOption = new VoteOption();
				voteOption.setEventId(eventID);
				voteOption.setPlace(place);
				if (multipartFiles[i].getOriginalFilename() != null
						&& !multipartFiles[i].getOriginalFilename().isEmpty()) {

					voteOption.setOptionImage(multipartFiles[i].getOriginalFilename());
					voteOption.setImageMuFile(multipartFiles[i]);

				} else {
					voteOption.setOptionImage(null);
					voteOption.setImageMuFile(null);
				}
				voteOptions.add(voteOption);
				i++;
			}
		}

		if (dates != null) {
			for (String date : dates) {
				voteOption = new VoteOption();
				voteOption.setEventId(eventID);
				voteOption.setStartDate(date);
				voteOptions.add(voteOption);
				voteOption.setOptionImage(null);
				voteOption.setImageMuFile(null);
			}

		}

		return voteOptions;

	}

	@Override
	public EventList getEventByid(int id) {
		String sql= "select * from [dbo].[Eventlist] where ID = ? ";
		ResultSet rs = null;
		EventList event ;
		try {
			rs=Database.SelectQueryString(sql, new Object[] {id});
			if(rs.next()) {
				event = new EventList();
					
				event.setId(rs.getInt("ID"));
				event.setCreatetor(rs.getString("Creator"));
				event.setEventName(rs.getString("EventName"));
				event.setDepartment("DepartName");
				event.setDescription(rs.getString("Description"));
				event.setTime(rs.getString("Time"));
				event.setPlace(rs.getString("Place"));
				event.setStatus(rs.getBoolean("Status"));
				event.setJoined(rs.getInt("Joined"));
				event.setNotJoined(rs.getInt("NotJoined"));
                event.setStartDate(rs.getDate("StartDate").toLocalDate());
                event.setEndDate(rs.getDate("EndDate").toLocalDate());
                event.setStartHour(rs.getTime("StartHour").toLocalTime());
                event.setEndHour(rs.getTime("EndHour").toLocalTime());
                event.setEventType(rs.getInt("EventTypeID"));
                return event;
 	
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 
		return null;	
	}	

}
