package com.zk.day3.test;

import java.util.List;

import org.junit.Test;

import com.ck.day3.dao.UserDAO;
import com.ck.day3.entity.User;

public class UserDAOTest {

	@Test
	public void testAddUser() {
		User user = new User();
		user.setName("张三");
		user.setPassword("123456");

		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);
	}

	@Test
	public void testUpdateUser() {
		User user = new User();
		user.setId(1);
		user.setPassword("654321");

		UserDAO userDAO = new UserDAO();
		userDAO.updateUser(user);
	}

	@Test
	public void testDelUser() {
		UserDAO userDAO = new UserDAO();
		userDAO.delUser(1);
	}

	@Test
	public void testGetUserById() {
		UserDAO userDAO = new UserDAO();
		User user = userDAO.getUserById(2);
		System.out.println(user);
	}

	@Test
	public void testGetUsers() {
		UserDAO userDAO = new UserDAO();
		// userDAO.getUsers().forEach(u -> System.out.println(u));
		List<User> users = userDAO.getUsers();
		for (User user : users) {
			System.out.println(user);
		}
	}
}
