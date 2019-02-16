package com.zk.day3.test;

import org.junit.Test;

import com.ck.day3.dao.StudentDAO;
import com.ck.day3.entity.Student;

public class StudentDAOTest {

	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setName("张三");
		student.setBirthdate("1999-1-1");
		student.setKlassId("9774e970e7394f39a5eaa108373fa0ad");
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.addStudent(student);

	}

	@Test
	public void testGetStudentById() {
		StudentDAO studentDAO = new StudentDAO();
		Student student = studentDAO.getStudentById(1);
		System.out.println(student);
	}

	@Test
	public void testStudents() {
		StudentDAO studentDAO = new StudentDAO();
		studentDAO.getStudents().forEach(s -> System.out.println(s));
	}

}
