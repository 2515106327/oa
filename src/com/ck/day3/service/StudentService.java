package com.ck.day3.service;

import java.util.List;

import com.ck.day3.dao.KlassDAO;
import com.ck.day3.dao.StudentDAO;
import com.ck.day3.entity.PageBean;
import com.ck.day3.entity.Student;

public class StudentService {
	public void addStudent(Student student) {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.addStudent(student);

		KlassDAO klassDAO = new KlassDAO();
		klassDAO.updateStuNum(student.getKlassId(), 1);
	}

	public PageBean<Student> getStudentsByPage(int page, Student student) {
		int pageSize = 5;
		PageBean<Student> pageBean = new PageBean<Student>();
		pageBean.setPage(page);
		pageBean.setPageSize(pageSize);

		StudentDAO studentDAO = new StudentDAO();
		List<Student> data = studentDAO.getStudentByPage(page, pageSize, student);
		pageBean.setData(data);
		int totalNum = studentDAO.getKlassCount(student);
		pageBean.setTotalNum(totalNum);

		pageBean.setActualSize(data.size());
		int totalPage = totalNum % pageSize == 0 ? totalNum / pageSize : totalNum / pageSize + 1;
		pageBean.setTotalPage(totalPage);

		return pageBean;

	}
	
	public List<Student> getStudents() {
		return  new StudentDAO().getStudents();
	}

	public Student getStudentById(int id) {
		StudentDAO studentDAO = new StudentDAO();
		return studentDAO.getStudentById(id);
	}

	public void updateStudent(Student student) {
		StudentDAO studentDAO = new StudentDAO();

		Student student2 = studentDAO.getStudentById(student.getId());
		student2.setName(student.getName());
		student2.setKlassId(student.getKlassId());

		studentDAO.updateStudent(student2);
	}

	public void deleteStudent(int id) {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.deleteStudent(id);
	}

	public boolean isExist(String name) {
		StudentDAO studentDAO = new StudentDAO();
		return studentDAO.isExist(name);
	}
	
	public List<Student> getStudentsByKlassId(String klassId) {
		StudentDAO studentDAO = new StudentDAO();
		return studentDAO.getStudentsByKlassId(klassId);
	}
}
