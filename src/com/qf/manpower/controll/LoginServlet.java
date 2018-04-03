package com.qf.manpower.controll;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stringMethod = request.getParameter("name");

		if ("top".equals(stringMethod)) {
			jumpPage("WEB-INF/jsp/top.jsp", request, response);
		}
		if ("left".equals(stringMethod)) {
			jumpPage("WEB-INF/jsp/left.jsp", request, response);
		}
		if ("right".equals(stringMethod)) {
			jumpPage("WEB-INF/jsp/right.jsp", request, response);
		}
	}

	/**
	 * 跳转页面的方法
	 * 
	 * @param string
	 */
	private void jumpPage(String string, HttpServletRequest request, HttpServletResponse response) {

		try {
			request.getRequestDispatcher(string).forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
