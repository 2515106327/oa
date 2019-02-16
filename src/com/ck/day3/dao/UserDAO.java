package com.ck.day3.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ck.day3.entity.User;

public class UserDAO {
	public void addUser(User user) {
		String sql = "insert into oa_user(name,password) values(?,?)";
		DbHelper.executeSQL(sql, user.getName(), user.getPassword());
	}

	public void updateUser(User user) {
		String sql = "update oa_user set name='" + user.getName() + "',password='" + user.getPassword() + "' where id='"
				+ user.getId() + "'";
		DbHelper.executeSQL(sql);
	}

	public void delUser(int id) {
		DbHelper.executeSQL("delete from oa_user where id='" + id + "'");
	}

	public User getUserById(int id) {
		User user = new User();
		String sql = "select id,name,password from oa_user where id=" + id;
		Connection conn = DbHelper.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public User getUserById2(int id) {
		String sql = "select id,name,password from oa_user where id=" + id;
		return DbHelper.getSingle(sql, User.class);
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		String sql = "select * from oa_user";
		Connection conn = DbHelper.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setPassword(rs.getString(3));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return users;
	}

	public User getUserByNameAndPwd(String name, String password) {
		return DbHelper.getSingle("select * from oa_user where name=? and password=?", User.class, name, password);
	}

	/**
	 * 
	 * @param name
	 * @return 如果存在，返回true;否则，返回false
	 */
	public boolean isExist(String name) {
		String sql = "select count(*) from oa_user where name='" + name + "'";
		Connection conn = DbHelper.getConnection();
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			return rs.getInt(1) != 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	public void updateArtar(int id, String avatar) {
		DbHelper.executeSQL("update oa_user set avatar=? where id=?", avatar, id);
	}
}
