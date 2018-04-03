package com.qf.manpower.controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.manpower.pojo.Dept;
import com.qf.manpower.service.IDeptService;
import com.qf.utils.ObjectUtils;

@WebServlet("/dept")
public class DeptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IDeptService deptService = null;

	public DeptServlet() {
		try {
			deptService = (IDeptService) ObjectUtils.getObj("deptService");
		} catch (Exception e) {
			System.out.println("获取部门service异常" + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringMethod = request.getParameter("method");
		Dept dept = new Dept();
		try {
			BeanUtils.populate(dept, request.getParameterMap());

			jumpMethod(stringMethod, request, response, dept);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 跳转的方法
	 * 
	 * @param stringMethod
	 * @param request
	 * @param response
	 * @param dept
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response, Dept dept) {
		/**
		 * 查找所有部门
		 */
		if ("findDeptAll".equals(stringMethod)) {
			findDeptAll(dept, request, response);

			/**
			 * 跳转到添加部门的页面
			 */
		} else if ("addDeptPage".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/dept/showAddDept.jsp", request, response);
			/**
			 * 跳转到添加部门
			 */
		} else if ("addDept".equals(stringMethod)) {
			addDept(dept, request, response);
			/**
			 * 跳转到修改部门的页面<通过id>
			 */
		} else if ("updateDeptPage".equals(stringMethod)) {
			findDeptById(dept, request, response);
			/**
			 * 删除部门
			 */
		} else if ("deleteDept".equals(stringMethod)) {
			deleteDept(dept, request, response);
			/**
			 * 模糊查找部门
			 */
		} else if ("findDeptByLike".equals(stringMethod)) {
			findDeptByLike(dept, request, response);
			/**
			 * 修改用户
			 */
		} else if ("updateDeptById".equals(stringMethod)) {
			updateDeptById(dept, request, response);
		}

	}

	/**
	 * 修改用户的方法
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void updateDeptById(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		try {
			deptService.updateDeptById(dept);

			jumpPage("/dept?method=findDeptAll", request, response);
		} catch (Exception e) {
			System.out.println("修改用户失败" + e.getMessage());
		}

	}

	/**
	 * 通过名字模糊查询部门
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void findDeptByLike(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dept> list = deptService.findDeptByDeptNameLike(dept);

			request.setAttribute("list", list);

		} catch (Exception e) {
			System.out.println("通过名字查询部门失败" + e.getMessage());
		} finally {
			jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
		}

	}

	/**
	 * 通过ID删除部门的方法
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void deleteDept(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		try {
			deptService.deleteDeptById(dept);

			jumpPage("/dept?method=findDeptAll", request, response);
		} catch (Exception e) {
			System.out.println("删除部门失败" + e.getMessage());
		}
	}

	/**
	 * 通过ID查找用户
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void findDeptById(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		try {
			Dept dept2 = deptService.findDeptById(dept);

			request.setAttribute("dept", dept2);

			jumpPage("/WEB-INF/jsp/dept/showUpdateDept.jsp", request, response);

		} catch (Exception e) {
			System.out.println("通过ID查找部门失败" + e.getMessage());
		}
	}

	/**
	 * 添加部门的方法
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void addDept(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		/**
		 * 添加部门的方法
		 */
		try {
			deptService.insert(dept);

			jumpPage("/dept?method=findDeptAll", request, response);
		} catch (Exception e) {
			System.out.println("添加部门失败" + e.getMessage());
		}
	}

	/**
	 * 查找所有部门的方法
	 * 
	 * @param dept
	 * @param request
	 * @param response
	 */
	private void findDeptAll(Dept dept, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Dept> list = deptService.findDeptAll();
			request.setAttribute("list", list);
			jumpPage("/WEB-INF/jsp/dept/dept.jsp", request, response);
		} catch (Exception e) {
			System.out.println("获取所有部门集合异常" + e.getMessage());
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
			System.out.println("跳转页面失败" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
