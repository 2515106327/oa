package com.ck.day3.service;

import java.util.List;

import com.ck.day3.dao.KlassDAO;
import com.ck.day3.entity.Klass;
import com.ck.day3.entity.PageBean;
import com.ck.day3.entity.Student;

public class KlassService {
//	private KlassDAOInterfaced klassDAO;
//	
	public void addKlass(Klass klass){
		KlassDAO klassDAO = new KlassDAO();
		klassDAO.addKlass(klass);
	}
	
	public PageBean<Klass> getKlassesByPage(int page) {
		int pageSize = 2;
		PageBean<Klass> pageBean = new PageBean<Klass>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);

		KlassDAO klassDAO = new KlassDAO();
		List<Klass> data = klassDAO.getKlassesByPage(page, pageSize);
		pageBean.setData(data);
		int totalNum = klassDAO.getKlassCount();
		pageBean.setTotalNum(totalNum);

		pageBean.setActualSize(data.size());
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		pageBean.setTotalPage(totalPage);

		return pageBean;

	}
	
	public List<Klass> getKlasses() {
		KlassDAO klassDAO = new KlassDAO();
		return klassDAO.getKlasses();
	}


//	public KlassDAOInterfaced getKlassDAO() {
//		return klassDAO;
//	}
//
//	public void setKlassDAO(KlassDAOInterfaced klassDAO) {
//		this.klassDAO = klassDAO;
//	}
}
