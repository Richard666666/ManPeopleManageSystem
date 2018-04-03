package com.qf.manpower.controll;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.manpower.pojo.Dept;
import com.qf.manpower.pojo.Employee;
import com.qf.manpower.pojo.Job;
import com.qf.manpower.service.IDeptService;
import com.qf.manpower.service.IEmployService;
import com.qf.manpower.service.IJobService;
import com.qf.utils.ObjectUtils;

import net.sf.json.JSONArray;

/**
 * Servlet implementation class EmployServlet
 */
@WebServlet("/employee")
public class EmployServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IEmployService employService = null;
	private IDeptService deptService = null;
	private IJobService jobService = null;

	public EmployServlet() {
		try {
			employService = (IEmployService) ObjectUtils.getObj("employService");

			deptService = (IDeptService) ObjectUtils.getObj("deptService");

			jobService = (IJobService) ObjectUtils.getObj("jobService");
		} catch (Exception e) {
			System.out.println("通过反射获取对象异常" + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringMethod = request.getParameter("method");
		Employee employee = new Employee();

		try {
			BeanUtils.populate(employee, request.getParameterMap());

			jumpMethod(stringMethod, request, response, employee);

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
	 * @param user
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response,
			Employee employee) {
		/**
		 * 跳转到查询所有员工页面，并展示
		 */
		if ("jumpEmploy".equals(stringMethod)) {
			findEmployAll(employee, request, response);

			/**
			 * 获取所有职位
			 */
		} else if ("findJob".equals(stringMethod)) {
			findJob(request, response);

			/**
			 * 删除员工
			 */
		} else if ("removeEmployee".equals(stringMethod)) {
			removeEmployee(employee, request, response);

			/**
			 * 跳转到修改员工的页面
			 */
		} else if ("updateEmployeePage".equals(stringMethod)) {
			updateEmployeePage(employee, request, response);
			/**
			 * 跳转到添加员工的页面
			 */
		} else if ("addEmployPage".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/employee/showAddEmployee.jsp", request, response);

			/**
			 * 获取所有的部门
			 */
		} else if ("findDept".equals(stringMethod)) {
			findDept(request, response);

			/**
			 * 更新员工
			 */
		} else if ("updateEmployee".equals(stringMethod)) {
			updateEmployee(employee, request, response);

			/**
			 * 添加员工
			 */
		} else if ("addEmployee".equals(stringMethod)) {
			addEmployee(employee, request, response);

			/**
			 * 模糊查询员工
			 */
		} else if ("findJobAndDept".equals(stringMethod)) {
			findJobAndDept(employee, request, response);
		}

	}

	/**
	 * 模糊查询员工的方法
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void findJobAndDept(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Employee> list = employService.findEmployByCC(employee);
			list = employList(list);
			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/employee/employee.jsp", request, response);

		} catch (Exception e) {
			System.out.println("模糊查询员异常" + e.getMessage());
		}
	}

	/**
	 * 添加员工的方法
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void addEmployee(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		employee.seteCreateTime(new Date());
		try {
			employService.insert(employee);

			jumpPage("/employee?method=jumpEmploy", request, response);
		} catch (Exception e) {
			System.out.println("添加员工异常" + e.getMessage());
		}
	}

	/**
	 * 更新员工的方法
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void updateEmployee(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		employee.seteCreateTime(new Date());

		try {
			employService.updateEmployById(employee);

			jumpPage("/employee?method=jumpEmploy", request, response);
		} catch (Exception e) {
			System.out.println("更新员工异常" + e.getMessage());
		}

	}

	/**
	 * 获取所有的部门的方法 JSON
	 * 
	 * @param request
	 * @param response
	 */
	private void findDept(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		List<Dept> list_Dept;
		try {
			list_Dept = deptService.findDeptAll();
			if (list_Dept != null && list_Dept.size() > 0) {
				response.getWriter().println(JSONArray.fromObject(list_Dept));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 跳转到修改员工的页面，并通过ID查找到员工，展示
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void updateEmployeePage(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		try {
			Employee employee2 = employService.findEmployById(employee);
			request.setAttribute("employee", employee2);

			jumpPage("/WEB-INF/jsp/employee/showUpdateEmployee.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过ID查找员工异常" + e.getMessage());
		}
	}

	/**
	 * 删除员工的方法
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void removeEmployee(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		try {
			employService.deleteEmployById(employee);

			jumpPage("/employee?method=jumpEmploy", request, response);
		} catch (Exception e) {
			System.out.println("删除员工异常" + e.getMessage());
		}
	}

	/**
	 * 获取所有职位的方法 JSON
	 * 
	 * @param request
	 * @param response
	 */
	private void findJob(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html;charset=utf-8");
		try {
			List<Job> list_Job = jobService.findJobAll();
			if (list_Job != null && list_Job.size() > 0) {
				response.getWriter().println(JSONArray.fromObject(list_Job));
			}
		} catch (Exception e) {
			System.out.println("获取所有部门和职位异常" + e.getMessage());
		}

	}

	/**
	 * 获取所有员工的方法，并展示
	 * 
	 * @param employee
	 * @param request
	 * @param response
	 */
	private void findEmployAll(Employee employee, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Employee> list = employService.findEmployAll();

			// 继续封装
			list = employList(list);

			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/employee/employee.jsp", request, response);
		} catch (Exception e) {
			System.out.println("获取所有员工方法 异常" + e.getMessage());
		}
	}

	/**
	 * 对返回的list集合继续封装，加入职位和部门对象
	 * 
	 * @param list
	 * @return
	 */
	private List<Employee> employList(List<Employee> list) {
		List<Employee> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			// 获取部门ID
			int dId = list.get(i).getdId();

			// 获取职位ID
			int jId = list.get(i).getjId();

			String dName = null;
			String jName = null;
			try {
				/**
				 * 通过id获取名字
				 */
				dName = deptService.findNameById(dId);
				jName = jobService.findNameById(jId);
			} catch (Exception e) {
				System.out.println("通过Id获取名字异常" + e.getMessage());
			}

			list.get(i).setDept(new Dept(0, dName, ""));
			list.get(i).setJob(new Job(0, jName, ""));
			newList.add(list.get(i));
		}

		return newList;
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
