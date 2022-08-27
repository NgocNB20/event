package service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import model.User;
import orm.paging.Page;
import orm.paging.PageAble;
import orm.paging.PageRequest;
import repository.UserRepositoty;
import repository.impl.UserRepositoryImpl;
import service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepositoty userRepositoty;

	@Override
	public boolean checkLogin(User user) {
		// TODO Auto-generated method stub
		return false;
	}

	 

	 
	


}
