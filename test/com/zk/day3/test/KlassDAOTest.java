package com.zk.day3.test;

import org.junit.Test;

import com.ck.day3.dao.KlassDAO;
import com.ck.day3.entity.Klass;
import com.ck.day3.util.OAUtil;

public class KlassDAOTest {

	@Test
	public void testAddKlass() {
		Klass klass=new Klass();
		klass.setId(OAUtil.getId());
		klass.setName("1班");
		
		KlassDAO klassDAO=new KlassDAO();
		klassDAO.addKlass(klass);
		
	}
	
	@Test
	public void testAddKlass2() {
		Klass klass=new Klass();
		klass.setId(OAUtil.getId());
		klass.setName("3班");
		
		KlassDAO klassDAO=new KlassDAO();
		klassDAO.addKlass(klass);
		
	}
	
	@Test
	public void testDelKlass() {
		
		KlassDAO klassDAO=new KlassDAO();
		klassDAO.delKlass("220aff5666dd4ae6bd88b500f89746be");
		
	}
	
	@Test
	public void testGetKlasses() {
		
		KlassDAO klassDAO=new KlassDAO();
		klassDAO.getKlasses().forEach(k->System.out.println(k));
		
	}
	
	@Test
	public void testGetKlassesByPage() {
		
		KlassDAO klassDAO=new KlassDAO();
		klassDAO.getKlassesByPage(2, 2).forEach(k->System.out.println(k));
		
	}
	
	@Test
	public void testGetKlassCount() {
		
		KlassDAO klassDAO=new KlassDAO();
		System.out.println(klassDAO.getKlassCount());
		
	}

}
