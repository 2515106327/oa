package com.ck.day3.util;

import java.util.UUID;

public class OAUtil {
	public static String getId() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().replaceAll("-", "");
	}

	public static void main(String[] args) {
		System.out.println(getId());
	}
}
