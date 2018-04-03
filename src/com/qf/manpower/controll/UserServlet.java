package com.qf.manpower.controll;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.manpower.pojo.User;
import com.qf.manpower.service.impl.UserService;

@WebServlet("/users")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stringMethod = request.getParameter("method");
		User user = new User();

		try {
			BeanUtils.populate(user, request.getParameterMap());

			jumpMethod(stringMethod, request, response, user);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 跳转方法
	 * 
	 * @param stringMethod
	 * @param request
	 * @param response
	 * @param user
	 * @throws IOException
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response, User user)
			throws IOException {
		/**
		 * 登录
		 */
		if ("login".equals(stringMethod)) {
			login(request, response, user);
			/**
			 * 退出登录
			 */
		} else if ("cancelLogin".equals(stringMethod)) {
			request.getSession().invalidate();

			response.sendRedirect(request.getContextPath() + "/index.jsp");
			/**
			 * 查询所有用户
			 */
		} else if ("finduserbyall".equals(stringMethod)) {
			find(request, response);
			/**
			 * 删除用户
			 */
		} else if ("deleteUser".equals(stringMethod)) {
			deleteUser(request, response, user);
			/**
			 * 跳转到添加用户的页面
			 */
		} else if ("adduser_".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/user/showAddUser.jsp", request, response);
			/**
			 * 添加用户
			 */
		} else if ("addUser".equals(stringMethod)) {
			addUser(user, request, response);
			/**
			 * 多条件查询用户
			 */
		} else if ("findUserByMore".equals(stringMethod)) {
			findUserByMore(user, request, response);
			/**
			 * 跳转到修改用户界面
			 */
		} else if ("showUpdateUser".equals(stringMethod)) {
			showUpdateUser(user, request, response);
			/**
			 * 修改用户
			 */
		} else if ("updateUser".equals(stringMethod)) {
			updateUser(user, request, response);
		}
	}

	/**
	 * 修改用户的方法 实现
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	private void updateUser(User user, HttpServletRequest request, HttpServletResponse response) {
		user.setuCreateTime(new Date());
		try {
			new UserService().updateUserById(user);

			User newuser = (User) request.getSession().getAttribute("user");
			if (user.getuId().intValue() == newuser.getuId().intValue()) {
				request.getSession().invalidate();
				// response.sendRedirect(request.getContextPath()+"/users?method=cancelLogin");
				jumpPage("/jump.jsp", request, response);

				// response.sendRedirect(request.getContextPath()+"/index.jsp");
			}

			jumpPage("/users?method=finduserbyall", request, response);
		} catch (Exception e) {
			System.out.println("修改用户失败" + e.getMessage());
		}

	}

	/**
	 * 跳转到修改用户的界面
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	private void showUpdateUser(User user, HttpServletRequest request, HttpServletResponse response) {
		try {
			User user2 = new UserService().findUserById(user);

			request.setAttribute("user", user2);

			jumpPage("/WEB-INF/jsp/user/showUpdateUser.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过ID查找用户失败" + e.getMessage());
		}
	}

	/**
	 * 多条件查询用户的方法
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	private void findUserByMore(User user, HttpServletRequest request, HttpServletResponse response) {

		try {
			List<User> list = new UserService().findUserByMore(user);

			request.setAttribute("list", list);

		} catch (Exception e) {
			System.out.println("查询用户失败" + e.getMessage());
		} finally {
			jumpPage("/WEB-INF/jsp/user/user.jsp", request, response);
		}

	}

	/**
	 * 添加用户的方法
	 * 
	 * @param user
	 * @param request
	 * @param response
	 */
	private void addUser(User user, HttpServletRequest request, HttpServletResponse response) {
		user.setuCreateTime(new Date());
		try {
			new UserService().insert(user);
			jumpPage("/users?method=finduserbyall", request, response);
		} catch (Exception e) {
			System.out.println("添加用户失败" + e.getMessage());
			jumpPage("/WEB-INF/jsp/user/showAddUser.jsp", request, response);
		}
	}

	/**
	 * 删除用户的方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 */
	private void deleteUser(HttpServletRequest request, HttpServletResponse response, User user) {
		User newuser = (User) request.getSession().getAttribute("user");
		if (user.getuId().intValue() != newuser.getuId().intValue()) {
			try {
				new UserService().deleteUserById(user);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("-----删除id出错-----");
			} finally {
				jumpPage("/users?method=finduserbyall", request, response);
			}
		} else {
			request.setAttribute("msg", "无法删除，当前正在登录的用户");
			jumpPage("/users?method=finduserbyall", request, response);
		}
	}

	/**
	 * 查找全部用户的方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 */
	private void find(HttpServletRequest request, HttpServletResponse response) {
		try {
			List<User> list = new UserService().findUserAll();

			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/user/user.jsp", request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 登录方法
	 * 
	 * @param request
	 * @param response
	 * @param user
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response, User user) throws IOException {
		try {
			User nUser = new UserService().finUserByUserNamePsw(user);
			request.getSession().setAttribute("user", nUser);

			jumpPage("/WEB-INF/jsp/main.jsp", request, response);
		} catch (Exception e) {
			e.printStackTrace();

			request.setAttribute("msg", "用户名或密码有误，请重新登录");
			// response.sendRedirect(request.getContextPath()+"/index.jsp");
			jumpPage("/WEB-INF/jsp/loginForm.jsp", request, response);
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
