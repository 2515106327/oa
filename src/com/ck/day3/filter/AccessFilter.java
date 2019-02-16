package com.ck.day3.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("*")
public class AccessFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// 得到访问的URL
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String uri = request.getRequestURI();
		System.out.println(uri + "===" + request.getRequestURL());
		// login.jsp
		if (uri.endsWith("/login.jsp") || uri.endsWith(".css") || uri.endsWith(".jpg") || uri.endsWith("/safecode")||uri.endsWith("/user")) {
			chain.doFilter(req, res);
		} else {
			// 获取Session
			HttpSession session = request.getSession();
			// 判断session中的属性user是否为空
			if (session.getAttribute("user") == null) {
				String path=request.getContextPath();
				System.out.println(path);
				response.sendRedirect(path+"/login.jsp");
			} else {
				chain.doFilter(req, res);
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
