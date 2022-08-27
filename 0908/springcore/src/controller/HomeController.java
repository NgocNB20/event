package controller;

import static utils.Util.convertArray;

import java.io.File;
import java.nio.file.Path;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.bcel.classfile.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import constant.ConstantData;
import database.Dao;
import model.Department;
import model.Detail;
import model.EventAdd;
import model.EventList;
import model.EventType;
import model.PageEventList;
import model.SearchEventList;
import model.User;
import model.VoteOption;
import orm.utils.StringUtils;
import repository.DepartmentRepository;
import repository.EventListRepositoty;
import repository.EventTypeRepository;
import repository.UserRepositoty;
import service.EventListService;
import service.EventService;
import service.UserService;

@Controller
public class HomeController {

	@Autowired
	EventListService eventListService;

	@Autowired
	EventService eventService;

	@Autowired
	DepartmentRepository departmentRepository;

	@Autowired
	EventTypeRepository eventTypeRepository;

	@Autowired
	UserRepositoty userRepositoty;

	@Autowired
	EventListRepositoty eventListRepositoty;

	@Autowired
	ServletContext aplication;

	@GetMapping(path = { "event-list" })
	public String goDashboard(HttpServletRequest request, SearchEventList search, HttpSession session) {
		int pageCurent = 1;
		String page = request.getParameter("page");
		if (StringUtils.notEmpty(page)) {
			pageCurent = Integer.valueOf(page);
		}
		if (session.getAttribute("username") == null || session.getAttribute("username").toString().isEmpty()) {
			return "redirect:/login";
		}
		List<Department> departments = departmentRepository.findAll();
		List<EventType> eventTypes = eventTypeRepository.findAll();
		request.setAttribute("eventTypes", eventTypes);
		request.setAttribute("departments", departments);
		// HocDV
		PageEventList events = eventListRepositoty.searchEventList(null, pageCurent - 1,
				session.getAttribute("username").toString());
		request.setAttribute("pages", events.getSearchData());
		request.setAttribute("indexs", convertArray((int) events.getTotalPage()));

		return "event-list";
	}

	@GetMapping(path = { "/", "login" })
	public String login(HttpServletRequest request, HttpSession session) {
		if (session.getAttribute("username") != null && !session.getAttribute("username").toString().isEmpty()) {
			return "redirect:/event-list";
		}
		return "login";
	}

	@GetMapping(path = "logout")
	public String logout(HttpServletRequest request, HttpSession session) {
		session.removeAttribute("username");
		session.removeAttribute("userId");
		return "redirect:/login";
	}

	@PostMapping(path = "/login")
	public String index(HttpServletRequest request, @ModelAttribute("user") User user, HttpSession session) {
		if (userRepositoty.checkUserNameAndPassword(user) != null) {
			session.setAttribute("username", user.getUsername());
			session.setAttribute("userId", user.getId());
			return "redirect:/event-list?page";
		}
		if (user.getPassword().trim().isEmpty() || user.getUsername().trim().isEmpty()) {
			request.setAttribute("message", "User or Passwork is  blank");
		} else {
			request.setAttribute("message", "User or Passwork is not valid");
		}

		return "login";
	}

	@RequestMapping("getByPage")
	public ModelAndView getByPage(HttpServletRequest request, ModelMap model, SearchEventList search,
			HttpSession session) {
		int pageCurent = 1;
		String page = request.getParameter("page");
		if (StringUtils.notEmpty(page)) {
			pageCurent = Integer.valueOf(page);
		}
		System.out.println(search.toString());
		System.out.println("CURRENT SEARCH" + page);
		PageEventList events = eventListRepositoty.searchEventList(search, pageCurent - 1,
				session.getAttribute("username").toString());
		model.addAttribute("pages", events.getSearchData());
		model.addAttribute("indexs", convertArray((int) events.getTotalPage()));

		ModelAndView mav = new ModelAndView();
		String viewName = "event-list-content";
		mav.setViewName(viewName);

		return mav;
	}

	@RequestMapping("loadUser")
	public ModelAndView loadUser(HttpServletRequest request, ModelMap model) {
		String departID = request.getParameter("departID");
		System.out.println("papapaap" + departID);
		List<User> users = userRepositoty.findByDepartID(Integer.valueOf(departID));
		System.out.println(users.size());
		model.addAttribute("members", users);
		ModelAndView mav = new ModelAndView();
		String viewName = "members";
		mav.setViewName(viewName);

		return mav;
	}

	@GetMapping("addEvent")
	public String addEvent(HttpServletRequest request, Model model) {
		List<Department> departments = departmentRepository.findAll();
		System.out.println(departments.get(0));
		List<User> usersDefault = userRepositoty.findByFirstDepartment(departments.get(0));
		List<EventType> eventTypes = eventTypeRepository.findAll();
		request.setAttribute("eventTypes", eventTypes);
		request.setAttribute("departments", departments);
		request.setAttribute("members", usersDefault);
		model.addAttribute("eventAdd", new EventAdd());
		System.out.println("AAAAAAAA" + usersDefault.size());
		return "add-form";
	}

	@PostMapping("addEvent")
	public String addEventPost(HttpServletRequest request, @ModelAttribute("eventAdd") EventAdd eventAdd, Model model,
			HttpSession session) {
		List<Department> departments = departmentRepository.findAll();
		System.out.println(departments.get(0));
		List<User> usersDefault = userRepositoty.findByFirstDepartment(departments.get(0));
		List<EventType> eventTypes = eventTypeRepository.findAll();
		request.setAttribute("eventTypes", eventTypes);
		request.setAttribute("departments", departments);
		request.setAttribute("members", usersDefault);
		eventAdd.setCreator(session.getAttribute("userId").toString());
		System.out.println(eventAdd.toString());

		System.out.println(eventAdd.toString());
		String realPath = request.getServletContext().getRealPath("/");
		String path = realPath + "resources\\image\\";
		System.out.println(path);
		eventListRepositoty.save(eventAdd, path);

		return "add-form";
	}

 
	@GetMapping(path = { "/details{id}" })
	public String Details(HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException {
		Integer idd = Integer.parseInt(request.getParameter("id"));
		EventList event = eventListRepositoty.getEventByid(idd);
		System.out.println(event.toString());
		request.setAttribute("event", event);
		List<Detail> details = new ArrayList<>();
		details = Dao.getDetail(idd);
		for (Detail d : details) {
			System.out.println(d.toString());
		}
		List<VoteOption> listOption = new ArrayList<>();
		listOption = Dao.getAllVote(idd);
		request.setAttribute("listOption", listOption);
		request.setAttribute("ListDetails", details);
		return "Event-Details";
	}

	@GetMapping(path = { "/update_details{id}" })
	public String UpdateEventDetail(HttpServletRequest request, Model model, HttpSession session)
			throws ClassNotFoundException, SQLException {
		Integer idd = Integer.parseInt(request.getParameter("id"));
		EventList event = eventListRepositoty.getEventByid(idd);
		System.out.println(event.toString());
		List<Detail> details = new ArrayList<>();
		details = Dao.getDetail(idd);
		for (Detail d : details) {
			System.out.println(d.toString());
		}
		List<VoteOption> listOption = new ArrayList<>();
		listOption = Dao.getAllVote(idd);
		model.addAttribute("eventAddModel", event);
		request.setAttribute("listOption", listOption);
		request.setAttribute("ListDetails", details);
		return "EventUpdate";
	}

	@PostMapping(path = { "/updateVote" })
	public String getVote(HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException {
		int idd = Integer.parseInt(request.getParameter("idds"));
		EventList event = eventListRepositoty.getEventByid(idd);
		List<VoteOption> listOption = new ArrayList<>();
		listOption = Dao.getAllVote(idd);
		List<Detail> details = new ArrayList<>();
		details = Dao.getDetail(idd);
		User userr = new User();
		if (event.getEventType() == 2) {
			try {
				for (Detail d : details) {
					String userid = String.valueOf(d.getUserId());
					Integer dUid = Integer.parseInt(request.getParameter(userid));
					userr = Dao.getUserById(dUid);
					String username = userr.getUsername();
					String dfullName = request.getParameter(d.getFullname());
					String dEmail = request.getParameter(d.getEmail());
					String joinOption = request.getParameter(d.getUserName() + "joinOption");
					System.out.println("Joinnnnnnnnnnn:" + d.getUserName() + "joinOption");
					String adult = String.valueOf(d.getAttachedPersonAdult() + d.getUserName() + "adult");
					int attachedPersonAdult = Integer.parseInt(request.getParameter(adult));
					String child = String.valueOf(d.getAttachedPersonChild() + d.getUserName() + "child");
					int attachedPersonChild = Integer.parseInt(request.getParameter(child));
					String note = request.getParameter(d.getNote() + d.getUserName() + "note");
					int isJoined = 0;
					if (joinOption.equalsIgnoreCase("O")) {
						isJoined = 1;
					}
					System.out.println("joinOption: " + joinOption);
					int co = 0;
					for (VoteOption o : listOption) {
						String getStartDate = request.getParameter(d.getUserName() + o.getStartDate() + "".trim());
						int isVoted = 1;
						if (getStartDate.equalsIgnoreCase("X")) {
							isVoted = 0;
						}
						;
						System.out.println("demmmmmmmmm: " + co);
						co++;
						System.out.println("joinOption: " + joinOption);
						System.out.println("Joinnnnnnnnnnn:" + d.getUserName() + "joinOption");
						System.out.println("username: " + username);
						System.out.println("eventid: " + idd);
						System.out.println("Joined: " + isJoined);
						System.out.println("VoteOptionid: " + o.getId());
						System.out.println("adult: " + attachedPersonAdult);
						String std = request.getParameter("startdate" + d.getUserName() + o.getStartDate() + "".trim());
						System.out.println("startDateOption: " + getStartDate);
						System.out.println("voteId: " + isVoted);
						System.out.println("start date: " + std);
						if (std.equalsIgnoreCase(o.getStartDate() + "")) {
							System.out.println("start date: " + std);
							System.out.println("start datedb: " + o.getStartDate());
							Dao.getVote(username, idd, isJoined, isVoted, o.getId(), attachedPersonAdult,
									attachedPersonChild, note);
						}
					}
				}
			} catch (Exception e) {
				System.out.println(e);
			}
		} else {
			for (Detail d : details) {
				String userid = String.valueOf(d.getUserId());
				Integer dUid = Integer.parseInt(request.getParameter(userid));
				userr = Dao.getUserById(dUid);
				String username = userr.getUsername();
				String dfullName = request.getParameter(d.getFullname());
				String dEmail = request.getParameter(d.getEmail());
				String joinOption = request.getParameter(d.getUserName() + "joinOption");
				String adult = String.valueOf(d.getAttachedPersonAdult() + d.getUserName() + "adult");
				int attachedPersonAdult = Integer.parseInt(request.getParameter(adult));
				String child = String.valueOf(d.getAttachedPersonChild() + d.getUserName() + "child");
				int attachedPersonChild = Integer.parseInt(request.getParameter(child));
				String note = request.getParameter(d.getNote() + d.getUserName() + "note");
				int isJoined = 0;
				if (joinOption.equalsIgnoreCase("O")) {
					isJoined = 1;
				}
				Dao.getVoteOptionType(new Object[] { isJoined, isJoined, attachedPersonAdult, attachedPersonChild, note,
						username, idd });

			}
		}
		details = Dao.getDetail(idd);
		listOption = Dao.getAllVote(idd);
		request.setAttribute("event", event);
		request.setAttribute("listOption", listOption);
		request.setAttribute("ListDetails", details);
		return "Event-Details";
	}
	
	@GetMapping(path = { "/vote" })
	public String showVote(HttpServletRequest request, HttpSession session) throws ClassNotFoundException, SQLException {
		 
		return "EventUpdate";
	}
	
	
	/// Duong

}
