package com.ck.day3.service;

import java.util.List;

import com.ck.day3.dao.DbHelper;
import com.ck.day3.dao.UserDAO;
import com.ck.day3.entity.User;

public class UserService {
	public void addUser(User user) {
		UserDAO userDAO = new UserDAO();
		if (userDAO.isExist(user.getName())) {
			return;
		}
		userDAO.addUser(user);
	}

	public List<User> getUsers() {
		return new UserDAO().getUsers();
	}

	public User login(String name, String password) {
		UserDAO userDAO = new UserDAO();
		return userDAO.getUserByNameAndPwd(name, password);
	}
	
	public void updateArtar(int id, String avatar) {
		UserDAO userDAO = new UserDAO();
		userDAO.updateArtar(id, avatar);
	}
}
