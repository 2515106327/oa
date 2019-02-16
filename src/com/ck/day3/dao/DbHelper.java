package com.ck.day3.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.ck.day3.exception.DAOException;

public class DbHelper {

	private static ThreadLocal<Connection> conns = new ThreadLocal<>();

	public static Connection getConnection() {
		System.out.println(Thread.currentThread().getId());
		Connection conn = conns.get();
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver"); // 加载驱动
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			try { // 获取连接
				conn = DriverManager.getConnection("jdbc:mysql:///oa?useUnicode=true&characterEncoding=UTF8", "root",
						"123456");
			} catch (SQLException e) {
				e.printStackTrace();
				throw new DAOException();
			}
			conns.set(conn);
		}
		return conn;
	}
	/*
	 * public static Connection getConnection() {
	 * 
	 * Connection conn = conns.get(); if (conn == null) { try { //通过JDNI获取数据源
	 * Context context = new InitialContext(); DataSource dataSource =
	 * (DataSource) context.lookup("java:comp/env/jdbc/testdb"); //从数据源得到连接 conn
	 * = dataSource.getConnection(); } catch (Exception e) {
	 * e.printStackTrace(); } conns.set(conn); } return conn; }
	 */

	public static void closeConnection(Connection connection) {
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
			conns.remove();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	public static void executeSQL(String sql, Object... params) {
		QueryRunner runner = new QueryRunner();
		try {
			runner.update(getConnection(), sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	public static <T> T getSingle(String sql, Class<T> c, Object... params) {
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(getConnection(), sql, new BeanHandler<>(c), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	public static <T> List<T> getResults(String sql, Class<T> c, Object... params) {
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(getConnection(), sql, new BeanListHandler<>(c), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

	public static <T> T getScalar(String sql, Class<T> c, Object... params) {
		QueryRunner runner = new QueryRunner();
		try {
			return runner.query(getConnection(), sql, new ScalarHandler<T>(), params);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DAOException();
		}
	}

}
