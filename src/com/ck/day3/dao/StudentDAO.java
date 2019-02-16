package com.ck.day3.dao;

import java.util.List;

import com.ck.day3.entity.Student;

public class StudentDAO {
	public void addStudent(Student student) {
		String sql = "insert into oa_student(name,birthdate,klassid) values(?,?,?)";
		DbHelper.executeSQL(sql, student.getName(), student.getBirthdate(), student.getKlassId());
	}

	public void updateStudent(Student student) {
		String sql = "update oa_student set name=?,birthdate=?,klassid=? where id=?";
		DbHelper.executeSQL(sql, student.getName(), student.getBirthdate(), student.getKlassId(), student.getId());
	}

	public void deleteStudent(int id) {
		String sql = "delete from oa_student where id=?";
		DbHelper.executeSQL(sql, id);
	}

	public Student getStudentById(int id) {
		String sql = "select s.*,k.name klassname from oa_student s,oa_klass k where s.klassid=k.id and s.id=?";
		return DbHelper.getSingle(sql, Student.class, id);
	}

	public List<Student> getStudents() {
		String sql = "select s.*,k.name klassname from oa_student s,oa_klass k where s.klassid=k.id";
		return DbHelper.getResults(sql, Student.class);
	}

	/**
	 * 根据班级ID查询班级中的学生
	 * @param klassId
	 * @return
	 */
	public List<Student> getStudentsByKlassId(String klassId) {
		String sql = "select s.*,k.name klassname from oa_student s,oa_klass k where s.klassid=k.id and k.id=?";
		return DbHelper.getResults(sql, Student.class,klassId);
	}
	/**
	 * 多条件分页查询
	 * 
	 * @param page
	 *            页码
	 * @param pageSize
	 *            每页数据量
	 * @param student
	 *            封装查询条件
	 * @return
	 */
	public List<Student> getStudentByPage(int page, int pageSize, Student student) {
		String sql = "select s.*,k.name klassname from oa_student s,oa_klass k where s.klassid=k.id "
				+ createCondition(student) + " limit ?,?";
		System.out.println(sql);
		return DbHelper.getResults(sql, Student.class, (page - 1) * pageSize, pageSize);
	}

	private String createCondition(Student student) {
		StringBuffer condition = new StringBuffer();

		if (student.getName() != null && student.getName().trim().length() != 0) {
			condition.append(" and s.name like '%" + student.getName() + "%'");
		}
		if (student.getKlassId() != null && !student.getKlassId().trim().equals("0")) {
			condition.append(" and klassid = '" + student.getKlassId() + "'");
		}

		return condition.toString();
	}

	public int getKlassCount(Student student) {
		return DbHelper.getScalar("select count(*) from oa_student s where 1=1 " + createCondition(student), long.class)
				.intValue();
	}

	/**
	 * 判断学生名是否存在
	 * @param name
	 * @return 如果存在，返回true;否则，返回false
	 */
	public boolean isExist(String name) {
		int num = DbHelper.getScalar("select count(*) from oa_student s where name=?", long.class, name).intValue();
		return num != 0;
	}
}
