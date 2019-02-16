package com.ck.day3.web;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.ck.day3.entity.User;
import com.ck.day3.service.UserService;
import com.ck.day3.util.OAUtil;

@WebServlet("/user")
@MultipartConfig
public class UserServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opr = request.getParameter("opr");
		if ("add".equals(opr)) {
			doAdd(request, response);
		} else if ("query".equals(opr)) {
			doQuery(request, response);
		} else if ("login".equals(opr)) {
			doLogin(request, response);
		} else if ("logout".equals(opr)) {
			doLogout(request, response);
		} else if ("updateAvatar".equals(opr)) {
			doUpdateAvrtar(request, response);
		}

	}
	
	private void doLogout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session=request.getSession();
		//session.invalidate();
		session.removeAttribute("user");
		response.sendRedirect("login.jsp");
	}

	private void doUpdateAvrtar(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Part part = request.getPart("avatar");
		String name = OAUtil.getId();
		String path = getServletContext().getRealPath("/images/avatar");
		part.write(path + "/" + name);
		User user = (User) request.getSession().getAttribute("user");
		UserService userService = new UserService();
		userService.updateArtar(user.getId(), "images/avatar/" + name);
		user.setAvatar("images/avatar/" + name);
		response.sendRedirect("user/avatar_update.jsp");
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String code = request.getParameter("code");
		String safeCode = request.getSession().getAttribute("safecode").toString();
		/*
		 * if (!code.equalsIgnoreCase(safeCode)) { request.setAttribute("msg",
		 * "验证码不正确!");
		 * request.getRequestDispatcher("login.jsp").forward(request, response);
		 * return; }
		 */

		UserService userService = new UserService();
		User user = userService.login(name, password);
		if (user != null) {
			String isRememer = request.getParameter("isRemember");
			if ("1".equals(isRememer)) {
				String time = request.getParameter("time");
				Cookie nameCookie = new Cookie("name", URLEncoder.encode(name, "UTF-8"));
				Cookie pwdCookie = new Cookie("password", password);
				if (time.equals("1")) {
					nameCookie.setMaxAge(60);
					pwdCookie.setMaxAge(60);
				} else if (time.equals("2")) {
					nameCookie.setMaxAge(60 * 60);
					pwdCookie.setMaxAge(60 * 60);
				}
				response.addCookie(nameCookie);
				response.addCookie(pwdCookie);
			}

			request.getSession().setAttribute("user", user);
			response.sendRedirect("index.jsp");
		} else {
			request.setAttribute("msg", "帐号或密码不正确!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
	}

	private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		UserService userService = new UserService();
		User user = new User();
		user.setName(name);
		user.setPassword(password);
		userService.addUser(user);
		response.sendRedirect("suc.html");
	}

	private void doQuery(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService userService = new UserService();
		List<User> users = userService.getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("user_list.jsp").forward(request, response);

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
