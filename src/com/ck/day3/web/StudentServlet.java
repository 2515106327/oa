package com.ck.day3.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ck.day3.entity.Klass;
import com.ck.day3.entity.PageBean;
import com.ck.day3.entity.Student;
import com.ck.day3.service.KlassService;
import com.ck.day3.service.ServiceProxyFactory;
import com.ck.day3.service.StudentService;

@WebServlet("/student")
public class StudentServlet extends HttpServlet {
	private StudentService studentService = ServiceProxyFactory.getService(StudentService.class);
	private KlassService klassService = ServiceProxyFactory.getService(KlassService.class);

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		if ("initadd".equals(opr)) {
			doInitAdd(request, response);
		} else if ("add".equals(opr)) {
			doAdd(request, response);
		} else if ("query".equals(opr)) {
			doQuery(request, response);
		} else if ("initupdate".equals(opr)) {
			doInitUpdate(request, response);
		} else if ("update".equals(opr)) {
			doUpdate(request, response);
		} else if ("del".equals(opr)) {
			doDel(request, response);
		} else if ("isexist".equals(opr)) {
			doIsExist(request, response);
		} else if ("ajaxquery".equals(opr)) {
			doAjaxQuery(request, response);
		} else if ("ajaxquerybyklassid".equals(opr)) {
			doAjaxQueryByKlassId(request, response);
		} else {

		}

	}
	private void doAjaxQueryByKlassId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String klassId=request.getParameter("klassid");
		List<Student> students = studentService.getStudentsByKlassId(klassId);

		StringBuilder json = new StringBuilder("[");
		for (Student student : students) {
			json.append("{");
			json.append("\"name\":\"" + student.getName() + "\"");
			json.append(",");
			json.append("\"birthdate\":\"" + student.getBirthdate() + "\"");
			json.append("}");
			json.append(",");
		}

		String data = json.substring(0, json.length() - 1);

		// json.append("]");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data + "]");
	}

	private void doAjaxQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Student> students = studentService.getStudents();

		StringBuilder json = new StringBuilder("[");
		for (Student student : students) {
			json.append("{");
			json.append("\"name\":\"" + student.getName() + "\"");
			json.append(",");
			json.append("\"birthdate\":\"" + student.getBirthdate() + "\"");
			json.append("}");
			json.append(",");
		}

		String data = json.substring(0, json.length() - 1);

		// json.append("]");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data + "]");
	}

	private void doIsExist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		boolean isExist = studentService.isExist(name);

		PrintWriter out = response.getWriter();
		out.print(isExist);

	}

	private void doDel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

		studentService.deleteStudent(Integer.parseInt(id));
		response.sendRedirect("student?opr=query");
	}

	private void doUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		// String birthdate = request.getParameter("birthdate");
		String klassId = request.getParameter("klassId");

		Student student = new Student();
		student.setId(Integer.parseInt(id));
		student.setName(name);
		// student.setBirthdate(birthdate);
		student.setKlassId(klassId);

		studentService.updateStudent(student);

		response.sendRedirect("student?opr=query");
	}

	private void doInitUpdate(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		Student student = studentService.getStudentById(Integer.parseInt(id));
		request.setAttribute("student", student);

		List<Klass> klasses = klassService.getKlasses();
		request.setAttribute("klasses", klasses);
		request.getRequestDispatcher("student/student_update.jsp").forward(request, response);
	}

	private void doInitAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Klass> klasses = klassService.getKlasses();
		request.setAttribute("klasses", klasses);
		request.getRequestDispatcher("student/student_add.jsp").forward(request, response);
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String birthdate = request.getParameter("birthdate");
		String klassId = request.getParameter("klassId");

		Student student = new Student();
		student.setName(name);
		student.setBirthdate(birthdate);
		student.setKlassId(klassId);

		studentService.addStudent(student);

		response.sendRedirect("student?opr=query");
	}

	private void doQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int page = 1;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		String name = request.getParameter("name");
		String klassId = request.getParameter("klassId");
		Student student = new Student();
		student.setName(name);
		student.setKlassId(klassId);

		request.setAttribute("student", student);

		PageBean<Student> pageBean = studentService.getStudentsByPage(page, student);
		request.setAttribute("pageBean", pageBean);

		List<Klass> klasses = klassService.getKlasses();
		request.setAttribute("klasses", klasses);
		request.getRequestDispatcher("student/student_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
