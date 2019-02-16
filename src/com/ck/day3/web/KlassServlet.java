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
import com.ck.day3.service.KlassService;
import com.ck.day3.service.ServiceProxyFactory;
import com.ck.day3.util.OAUtil;

@WebServlet("/klass")
public class KlassServlet extends HttpServlet {

	KlassService klassService = ServiceProxyFactory.getService(KlassService.class);

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(Thread.currentThread().getId());
		request.setCharacterEncoding("UTF-8");
		String opr = request.getParameter("opr");
		if ("initadd".equals(opr)) {
			doInitAdd(request, response);
		} else if ("add".equals(opr)) {
			doAdd(request, response);
		} else if ("query".equals(opr)) {
			doQuery(request, response);
		} else if ("ajaxquery".equals(opr)) {
			doAjaxQuery(request, response);
		} else {

		}
	}
	private void doAjaxQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Klass> klasses = klassService.getKlasses();

		StringBuilder json = new StringBuilder("[");
		for (Klass klass : klasses) {
			json.append("{");
			json.append("\"id\":\"" + klass.getId() + "\"");
			json.append(",");
			json.append("\"name\":\"" + klass.getName() + "\"");
			json.append("}");
			json.append(",");
		}

		String data = json.substring(0, json.length() - 1);

		// json.append("]");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print(data + "]");
	}

	private void doInitAdd(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("klass/klass_add.jsp").forward(request, response);
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");

		Klass klass = new Klass();
		klass.setId(OAUtil.getId());
		klass.setName(name);

		klassService.addKlass(klass);
		response.sendRedirect("klass?opr=query");
	}

	private void doQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PageBean<Klass> pageBean = klassService.getKlassesByPage(1);
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("klass/klass_list.jsp").forward(request, response);
		// response.sendRedirect("klass/klass_list.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
