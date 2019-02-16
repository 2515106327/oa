package com.ck.day3.dao;

import java.util.List;

import com.ck.day3.entity.Klass;

public interface KlassDAOInterfaced {
	public void addKlass(Klass klass);

	public void delKlass(String id);

	public List<Klass> getKlasses();

	public List<Klass> getKlassesByPage(int page, int pageSize);

	public int getKlassCount();

	public void updateStuNum(String id, int stuNum);
}
