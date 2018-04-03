package com.qf.manpower.controll;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.qf.manpower.pojo.Job;
import com.qf.manpower.service.IJobService;
import com.qf.utils.ObjectUtils;

/**
 * Servlet implementation class JobSerclet
 */
@WebServlet("/job")
public class JobSerclet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IJobService jobService = null;

	/**
	 * 通过反射获取对象
	 */
	public JobSerclet() {
		try {
			jobService = (IJobService) ObjectUtils.getObj("jobService");
		} catch (Exception e) {
			System.out.println("通过反射获取jobService异常" + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringMethod = request.getParameter("method");
		Job job = new Job();

		try {
			BeanUtils.populate(job, request.getParameterMap());

			jumpMethod(stringMethod, request, response, job);

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
	 * @param job
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response, Job job) {
		/**
		 * 查找所有的职位
		 */
		if ("findJobAll".equals(stringMethod)) {
			findJobAll(job, request, response);

			/**
			 * 跳转到添加职位页面
			 */
		} else if ("addJobPage".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/job/showAddJob.jsp", request, response);

			/**
			 * 添加职位
			 */
		} else if ("addJob".equals(stringMethod)) {
			addJob(job, request, response);

			/**
			 * 删除职位
			 */
		} else if ("removeJob".equals(stringMethod)) {
			deleteJob(job, request, response);

			/**
			 * 模糊查找 通过职位名称
			 */
		} else if ("findJobByLike".equals(stringMethod)) {
			findJobByLike(job, request, response);

			/**
			 * 跳转到修改页面
			 */
		} else if ("updateJobPage".equals(stringMethod)) {
			updatePage(job, request, response);

			/**
			 * 更改职位
			 */
		} else if ("updateJob".equals(stringMethod)) {
			updateJobById(job, request, response);
		}
	}

	/**
	 * 更改职位的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void updateJobById(Job job, HttpServletRequest request, HttpServletResponse response) {
		try {
			jobService.updateJobById(job);

			jumpPage("/job?method=findJobAll", request, response);
		} catch (Exception e) {
			System.out.println("更新职位异常" + e.getMessage());
		}

	}

	/**
	 * 跳转到修改页面并通过ID查到职位展示的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void updatePage(Job job, HttpServletRequest request, HttpServletResponse response) {
		try {
			Job job2 = jobService.findJobById(job);

			request.setAttribute("job", job2);

			jumpPage("/WEB-INF/jsp/job/showUpdateJob.jsp", request, response);

		} catch (Exception e) {
			System.out.println("通过ID查找职位异常" + e.getMessage());
		}

	}

	/**
	 * 通过名称模糊查询的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void findJobByLike(Job job, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Job> list = jobService.findJobByNameLike(job);

			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过职位模糊查询失败" + e.getMessage());
		}
	}

	/**
	 * 删除职位的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void deleteJob(Job job, HttpServletRequest request, HttpServletResponse response) {

		try {
			jobService.deleteJobById(job);

			jumpPage("/job?method=findJobAll", request, response);
		} catch (Exception e) {
			System.out.println("删除职位异常" + e.getMessage());
		}
	}

	/**
	 * 添加职位的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void addJob(Job job, HttpServletRequest request, HttpServletResponse response) {

		try {
			jobService.insert(job);

			jumpPage("/job?method=findJobAll", request, response);
		} catch (Exception e) {
			System.out.println("添加职位异常" + e.getMessage());
		}
	}

	/**
	 * 查找所有的职位的方法
	 * 
	 * @param job
	 * @param request
	 * @param response
	 */
	private void findJobAll(Job job, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Job> list = jobService.findJobAll();

			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/job/job.jsp", request, response);
		} catch (Exception e) {
			System.out.println("查询所有职位异常" + e.getMessage());
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
