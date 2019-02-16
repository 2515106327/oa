package com.zk.day3.test;

import org.junit.Test;

import com.ck.day3.entity.Student;
import com.ck.day3.service.ServiceProxyFactory;
import com.ck.day3.service.StudentService;

public class StudentServiceTest {
	@Test
	public void testAddStudent() {
		Student student = new Student();
		student.setName("Stu5");
		student.setBirthdate("1999-1-1");
		student.setKlassId("9774e970e7394f39a5eaa108373fa0ad");

		StudentService studentService = ServiceProxyFactory.getService(StudentService.class);
		studentService.addStudent(student);
	}
}
