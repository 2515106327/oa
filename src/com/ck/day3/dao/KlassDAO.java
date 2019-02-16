package com.ck.day3.dao;

import java.util.List;

import com.ck.day3.entity.Klass;

public class KlassDAO implements KlassDAOInterfaced{

	public void addKlass(Klass klass) {
		DbHelper.executeSQL("insert into oa_klass values(?,?,0)", klass.getId(), klass.getName());
	}

	public void delKlass(String id) {
		DbHelper.executeSQL("delete from oa_klass where id=?", id);
	}

	public List<Klass> getKlasses() {
		return DbHelper.getResults("select id,name,stunum from oa_klass", Klass.class);
	}

	public List<Klass> getKlassesByPage(int page, int pageSize) {
		String sql = "select * from oa_klass limit ?,?";
		return DbHelper.getResults(sql, Klass.class, (page - 1) * pageSize, pageSize);
	}
	
	public int getKlassCount(){
		return DbHelper.getScalar("select count(*) from oa_klass", long.class).intValue();
	}
	
	public void updateStuNum(String id,int stuNum){
		DbHelper.executeSQL("update oa_klass set stunum=stunum+? where id=?", stuNum,id);
	}

}
