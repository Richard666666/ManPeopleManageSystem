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

import com.qf.manpower.pojo.Notice;
import com.qf.manpower.pojo.User;
import com.qf.manpower.service.INoticeService;
import com.qf.manpower.service.IUserService;
import com.qf.utils.ObjectUtils;

@WebServlet("/notice")
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private INoticeService noticeService = null;
	private IUserService userService = null;

	public NoticeServlet() {
		try {
			noticeService = (INoticeService) ObjectUtils.getObj("noticeService");
			userService = (IUserService) ObjectUtils.getObj("userService");
		} catch (Exception e) {
			System.out.println("通过反射获取对象失败" + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String stringMethod = request.getParameter("method");
		Notice notice = new Notice();

		try {
			BeanUtils.populate(notice, request.getParameterMap());

			jumpMethod(stringMethod, request, response, notice);

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
	 * @param notice
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response,
			Notice notice) {
		/**
		 * 查找所有公告
		 */
		if ("findNoticeAll".equals(stringMethod)) {
			findNoticeAll(notice, request, response);

			/**
			 * 跳转到添加公告的页面
			 */
		} else if ("addNoticePage".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/notice/showAddNotice.jsp", request, response);

			/**
			 * 添加公告
			 */
		} else if ("addNotice".equals(stringMethod)) {
			addNotice(notice, request, response);

			/**
			 * 删除公告
			 */
		} else if ("removeNotice".equals(stringMethod)) {
			removeNotive(notice, request, response);

			/**
			 * 通过标题和内容模糊查询公告
			 */
		} else if ("selectNotice".equals(stringMethod)) {
			selectNotice(notice, request, response);

			/**
			 * 预览公告
			 */
		} else if ("previewNotice".equals(stringMethod)) {
			previewNotice(notice, request, response);

			/**
			 * 更改公告的跳转
			 */
		} else if ("updateNoticePage".equals(stringMethod)) {
			updateNoticePage(notice, request, response);

			/**
			 * 更改公告
			 */
		} else if ("updateNotice".equals(stringMethod)) {
			updateNotice(notice, request, response);
		}

	}

	/**
	 * 更改公告的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void updateNotice(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		notice.setnCreateTime(new Date());
		User user = (User) request.getSession().getAttribute("user");
		notice.setuId(user.getuId());
		try {
			noticeService.updateNoticeByid(notice);

			jumpPage("/notice?method=findNoticeAll", request, response);
		} catch (Exception e) {
			System.out.println("更改公告异常" + e.getMessage());
		}
	}

	/**
	 * 更改公告跳转并查询展示的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void updateNoticePage(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		try {
			Notice notice2 = noticeService.findNoticeById(notice);
			request.setAttribute("notice", notice2);
			jumpPage("/WEB-INF/jsp/notice/showUpdateNotice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过ID查询公告失败" + e.getMessage());
		}
	}

	/**
	 * 预览公告的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void previewNotice(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		try {
			Notice notice2 = noticeService.findNoticeById(notice);

			request.setAttribute("notice", notice2);
			jumpPage("/WEB-INF/jsp/notice/previewNotice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过ID获取公告异常" + e.getMessage());
		}
	}

	/**
	 * 通过名字标题,标题模糊查询的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void selectNotice(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Notice> list = noticeService.findNoticeByNameAndContentLike(notice);
			list = employList(list);
			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("模糊查询公告异常" + e.getMessage());
		}
	}

	/**
	 * 删除公告的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void removeNotive(Notice notice, HttpServletRequest request, HttpServletResponse response) {

		try {
			noticeService.deleteNoticeById(notice);
			jumpPage("/notice?method=findNoticeAll", request, response);
		} catch (Exception e) {
			System.out.println("删除公告异常" + e.getMessage());
		}
	}

	/**
	 * 添加公告的方法
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void addNotice(Notice notice, HttpServletRequest request, HttpServletResponse response) {

		notice.setnCreateTime(new Date());
		User user = (User) request.getSession().getAttribute("user");
		notice.setuId(user.getuId());
		try {
			noticeService.insert(notice);

			jumpPage("/notice?method=findNoticeAll", request, response);
		} catch (Exception e) {
			System.out.println("添加公告异常" + e.getMessage());

		}

	}

	/**
	 * 查找所有公告，并将查询结果展示到指定的页面
	 * 
	 * @param notice
	 * @param request
	 * @param response
	 */
	private void findNoticeAll(Notice notice, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Notice> list = noticeService.findNoticeAll();
			list = employList(list);
			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/notice/notice.jsp", request, response);
		} catch (Exception e) {
			System.out.println("获取所有公告方法 异常" + e.getMessage());
		}
	}

	/**
	 * 对查询到全部公告list集合继续封装
	 * 
	 * @param list
	 * @return
	 */
	private List<Notice> employList(List<Notice> list) {
		ArrayList<Notice> newList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			int uId = list.get(i).getuId();

			try {
				String uName = userService.findNameById(uId);

				list.get(i).setUser((new User(0, uName, "", "", 0, new Date())));
				newList.add(list.get(i));
			} catch (Exception e) {
				System.out.println("通过ID获取名字异常" + e.getMessage());
			}
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
