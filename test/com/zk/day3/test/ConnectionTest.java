package com.zk.day3.test;

import org.junit.Test;

import com.ck.day3.dao.DbHelper;

public class ConnectionTest {
	@Test
	public void testGetConnection() {
		System.out.println(DbHelper.getConnection());
	}
}
