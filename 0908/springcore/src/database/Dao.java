package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Detail;
import model.RegistEvent;
import model.User;
import model.VoteDetail;
import model.VoteOption;

public class Dao {

	public Dao() {
		// TODO Auto-generated constructor stub
	}

	public static List<Detail> getDetail(int id) throws ClassNotFoundException, SQLException {
		String sql = "select DISTINCT u.ID, u.Fullname, u.Email, r.IsJoined, r.AttachedPersonAdult,r.AttachedPersonChild,r.Note,u.userName"
				+ " from [dbo].[Users] u right join [dbo].[RegistEvents] r on r.Username= u.Username "
				+ "right join [dbo].[EventList] e on e.ID=r.EventID "
				+ "where e.ID=? group by u.ID,u.Fullname,u.Email,r.IsJoined,"
				+ "r.AttachedPersonAdult,r.AttachedPersonChild,r.Note,r.Voted,u.Username";
		System.out.println("SQL" + sql);
		try (Connection con = Database.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setObject(1, id);
			ResultSet rs = ps.executeQuery();

			List<Detail> details = new ArrayList<>();
			int i = 0;
			while (rs.next()) {
				Detail detail = new Detail();
				detail.setId(i);
				detail.setUserId(rs.getInt("ID"));
				detail.setFullname(rs.getString("Fullname"));
				detail.setEmail(rs.getString("Email"));
				detail.setIsJoined(rs.getBoolean("IsJoined"));
				detail.setAttachedPersonAdult(rs.getInt("AttachedPersonAdult"));
				detail.setAttachedPersonChild(rs.getInt("AttachedPersonChild"));
				detail.setNote(rs.getString("Note"));
				detail.setUserName(rs.getString("userName"));

				// Get thong tin vote
				String voteQuery = "select VoteOptionID, Voted from RegistEvents where EventID = ? and Username = ?";
				ResultSet rs1 = Database.SelectQueryString(voteQuery, new Object[] { id, detail.getUserName() });
				Map<Integer, Integer> voteMap = new HashMap<Integer, Integer>();
				while (rs1.next()) {
					voteMap.put(rs1.getInt(1), rs1.getInt(2));
				}
				detail.setVoteMap(voteMap);

				// Add vao list
				details.add(detail);

				i++;
			}
			return details;

		}

	}

	public static void updatePersonandNote(Object[] obj) throws ClassNotFoundException, SQLException {
		String sql = "update RegistEvents set IsJoined =?, AttachedPersonAdult=?,AttachedPersonChild=?,Note=?\r\n"
				+ "where Username=? and EventID= ?";
		Database.ModifyQueryString(sql, obj);

	}

	public static List<String> getUserByEventID(int id) throws ClassNotFoundException, SQLException {
		String q = "select	DISTINCT Username from RegistEvents where EventID = ?";
		ResultSet rs = Database.SelectQueryString(q, new Object[] { id });
		List<String> lst = new ArrayList<String>();
		while (rs.next()) {
			lst.add(rs.getString(1));
		}
		return lst;

	}

	public static List<VoteOption> getAllVote(int EventID) throws ClassNotFoundException, SQLException {
		String sql = "select * from VoteOption where VoteOption.EventID = ?";
		List<VoteOption> listOption = new ArrayList<>();
		ResultSet rs = null;
		rs = Database.SelectQueryString(sql, new Object[] { EventID });
		while (rs.next()) {
			VoteOption voteOption = new VoteOption();
			voteOption.setId(Integer.valueOf(rs.getObject("ID").toString()));
			
            if(rs.getObject("StartDate")!=null&&!rs.getObject("StartDate").toString().trim().isEmpty()) {
            	System.out.println("=!!!!NUll");
            	voteOption.setStartDate(rs.getObject("StartDate").toString());
            }
            if(rs.getObject("EventID")!=null&&!rs.getObject("EventID").toString().trim().isEmpty()) {
            	voteOption.setEventId(Integer.valueOf(rs.getObject("EventID").toString()));
            }
            if(rs.getObject("OptionImage")!=null&&!rs.getObject("OptionImage").toString().trim().isEmpty()) {
            	voteOption.setOptionImage(rs.getString("OptionImage"));
            }
			
			voteOption.setEventId(Integer.valueOf(rs.getObject("EventID").toString()));
            if(rs.getObject("Place")!=null&&!rs.getObject("Place").toString().trim().isEmpty()) {
            	voteOption.setPlace(rs.getObject("Place").toString());
            }
			
			
			voteOption.setIsDeleted(rs.getBoolean("IsDeleted"));

			listOption.add(voteOption);
		}
		
		return listOption;
	}

	private String convertBlank(Object object) {
		return "";
	}

	public static User getUserById(Integer id) throws SQLException, ClassNotFoundException {
		String sql = "select * from Users where ID=?";
		ResultSet rs = null;
		System.out.println("IDDD" + id);
		rs = Database.SelectQueryString(sql, new Object[] { id });
		if (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("ID"));
			user.setDepartID(rs.getInt("DepartID"));
			user.setEmail(rs.getString("Email"));
			user.setFullName(rs.getString("Fullname"));
			user.setIsDeleted(rs.getBoolean("IsDeleted"));
			user.setPassword(rs.getString("Password"));
			user.setUsername(rs.getString("UserName"));

			return user;
		}
		return null;
	}

	public static void getVoteOptionType(Object[] obj) throws ClassNotFoundException, SQLException {
		String Updatesql = "update RegistEvents set IsJoined =?,Voted=?,AttachedPersonAdult=?,AttachedPersonChild=?,Note=? \r\n"
				+ "where  Username=? and EventID = ?  ";
		Database.ModifyQueryString(Updatesql, obj);
	}

	public static void getVote(String Username, int EventID, int IsJoined, int Voted, int voteOptionID,
			int AttachedPersonA, int AttachedPersonC, String Note) throws ClassNotFoundException, SQLException {
		String updateVote = "update RegistEvents set Voted = ? where Username=? and EventID= ? and VoteOptionID = ?";
		String insertVote = "INSERT INTO [dbo].[RegistEvents] (Username, EventID, IsJoined, VoteOptionID, Voted, AttachedPersonAdult,\r\n"
				+ "AttachedPersonChild,IsDeleted,Note) VALUES (?,?,?,?,?,?,?,DEFAULT,?)";
		String testData = "select count(*) from RegistEvents where Username=? and EventID= ? and VoteOptionID = ?";
		ResultSet rs = Database.SelectQueryString(testData, new Object[] { Username, EventID, voteOptionID });
		rs.next();

		int count = rs.getInt(1);
		if (count == 0) {
			Database.ModifyQueryString(insertVote, new Object[] { Username, EventID, IsJoined, voteOptionID, Voted,
					AttachedPersonA, AttachedPersonC, Note });
		} else {
			Database.ModifyQueryString(updateVote, new Object[] { Voted, Username, EventID, voteOptionID });
			updatePersonandNote(new Object[] { IsJoined, AttachedPersonA, AttachedPersonC, Note, Username, EventID });
		}
	}

	public static List<VoteDetail> getVoteDetail(int optionType, int uId) throws ClassNotFoundException, SQLException {
		String EventID2 = "select e.ID, e.EventName, v.StartDate, v.OptionImage,r.Voted,u.ID as userid,r.IsJoined from VoteOption v right join [dbo].[Events] e \r\n"
				+ "on v.EventID = e.ID right join dbo.RegistEvents r on r.EventID =e.ID right join dbo.Users u on r.Username=u.Username\r\n"
				+ "where e.OptionType = 2 and u.ID=? and v.ID=r.VoteOptionID";
		String EventID1 = "select e.ID, e.EventName, e.StartDate,e.EventImage,r.Voted,u.ID as userid,r.IsJoined \r\n"
				+ "from  [dbo].[Events] e \r\n"
				+ " right join dbo.RegistEvents r on r.EventID =e.ID right join dbo.Users u on r.Username=u.Username\r\n"
				+ "where e.OptionType = 1 and u.ID=?";
		List<VoteDetail> voteDetail = new ArrayList<>();
		ResultSet rs = null;
		if (optionType == 1) {
			rs = Database.SelectQueryString(EventID1, new Object[] { uId });
		} else if (optionType == 2) {
			rs = Database.SelectQueryString(EventID2, new Object[] { uId });
		}
		while (rs.next()) {
			voteDetail.add(new VoteDetail(rs.getInt(6), rs.getInt(1), rs.getString(2), rs.getDate(3).toLocalDate(),
					rs.getString(4), rs.getBoolean(5), rs.getBoolean(7)));
		}

		return voteDetail;
	}

}
