package com.qf.manpower.controll;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.IOUtils;

import com.qf.manpower.pojo.Download;
import com.qf.manpower.pojo.User;
import com.qf.manpower.service.IDownLoadService;
import com.qf.manpower.service.IUserService;
import com.qf.utils.DownLoadUtils;
import com.qf.utils.ObjectUtils;
import com.qf.utils.UploadUtils;

/**
 * Servlet implementation class DocumentServlet
 */
@WebServlet("/document")
@MultipartConfig
public class DocumentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IDownLoadService downLoadService = null;
	private IUserService userService = null;

	public DocumentServlet() {
		try {
			downLoadService = (IDownLoadService) ObjectUtils.getObj("downLoadService");
			userService = (IUserService) ObjectUtils.getObj("userService");

		} catch (Exception e) {
			System.out.println("反射获取对象异常" + e.getMessage());
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String stringMethod = request.getParameter("method");
		Download download = new Download();
		try {
			BeanUtils.populate(download, request.getParameterMap());

			jumpMethod(stringMethod, request, response, download);

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
	 * @param download
	 */
	private void jumpMethod(String stringMethod, HttpServletRequest request, HttpServletResponse response,
			Download download) {
		/**
		 * 查询所有
		 */
		if ("findDocumentPage".equals(stringMethod)) {
			findDocumentPage(download, request, response);

			/**
			 * 上传文档的页面跳转
			 */
		} else if ("addDocumentPage".equals(stringMethod)) {
			jumpPage("/WEB-INF/jsp/document/showAddDocument.jsp", request, response);

			/**
			 * 删除文档
			 */
		} else if ("removeDocument".equals(stringMethod)) {
			removeDocument(download, request, response);

			/**
			 * 通过标题模糊查询
			 */
		} else if ("findDocumentByLike".equals(stringMethod)) {
			findDocumentByLike(download, request, response);

			/**
			 * 添加文档and上传
			 */
		} else if ("addDocument".equals(stringMethod)) {
			addDocument(download, request, response);

			/**
			 * 更新文档的页面跳转
			 */
		} else if ("updateDocumentPage".equals(stringMethod)) {
			updateDocumentPage(download, request, response);

			/**
			 * 更新文档
			 */
		} else if ("updateDocument".equals(stringMethod)) {
			updateDocument(download, request, response);

			/**
			 * 下载文档
			 */
		} else if ("downLoad".equals(stringMethod)) {
			download(download, request, response);

		}

	}

	/**
	 * 文件下载
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void download(Download download, HttpServletRequest request, HttpServletResponse response) {
		try {
			Download download2 = downLoadService.findDownLoadById(download);

			String fifleName = download2.getDoTitle();

			fileDownLoad(request, response, fifleName);

		} catch (Exception e) {
			System.out.println("" + e.getMessage());
		}

	}

	private void fileDownLoad(HttpServletRequest request, HttpServletResponse response, String fifleName)
			throws UnsupportedEncodingException, IOException {

		ServletContext context = this.getServletContext();
		String mimeType = context.getMimeType(fifleName);
		response.setContentType(mimeType);
		String _name = DownLoadUtils.getName(request.getHeader("user-agent"), fifleName);
		response.setHeader("content-disposition", "attachment;filename=" + _name);
		InputStream is = context.getResourceAsStream("/DownLoad/" + fifleName);
		ServletOutputStream os = response.getOutputStream();
		IOUtils.copy(is, os);
		is.close();
		os.close();
	}

	/**
	 * 更新文档的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void updateDocument(Download download, HttpServletRequest request, HttpServletResponse response) {
		download.setDoCreateTime(new Date());
		User user = (User) request.getSession().getAttribute("user");
		download.setuId(user.getuId());
		try {
			downLoadService.updateDownLoadById(download);
			upFile(request);
			jumpPage("/document?method=findDocumentPage", request, response);
		} catch (Exception e) {
			System.out.println("更新文档异常" + e.getMessage());
		}

	}

	/**
	 * 更新的页面跳转的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void updateDocumentPage(Download download, HttpServletRequest request, HttpServletResponse response) {
		try {
			Download download2 = downLoadService.findDownLoadById(download);

			request.setAttribute("document", download2);

			jumpPage("/WEB-INF/jsp/document/showUpdateDocument.jsp", request, response);
		} catch (Exception e) {
			System.out.println("通过ID查询异常" + e.getMessage());
		}

	}

	/**
	 * 添加文档的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void addDocument(Download download, HttpServletRequest request, HttpServletResponse response) {
		download.setDoCreateTime(new Date());
		User user = (User) request.getSession().getAttribute("user");
		download.setuId(user.getuId());
		try {
			downLoadService.insert(download);
			// 上传的封装
			upFile(request);
			jumpPage("/document?method=findDocumentPage", request, response);
		} catch (Exception e) {
			System.out.println("添加下载文档方法异常" + e.getMessage());
		}

	}

	/**
	 * 文件的上传方法
	 * 
	 * @param request
	 * @throws IOException
	 * @throws ServletException
	 * @throws FileNotFoundException
	 */
	private void upFile(HttpServletRequest request) throws IOException, ServletException, FileNotFoundException {
		Part part = request.getPart("file");

		String string = part.getHeader("content-disposition");

		String fileName = string.substring(string.indexOf("filename=") + 10, string.length() - 1);
		System.out.println(fileName);

		String uuidName = UploadUtils.getUUIDName(fileName);
		System.out.println(uuidName);
		// 文件存放的随机目录
		// String dir = UploadUtils.getDir(uuidName);

		String realPath = getServletContext().getRealPath("download");

		File file = new File(realPath);// 文件夹
		if (!file.exists()) {
			file.mkdirs();
		}

		InputStream inputStream = part.getInputStream();

		FileOutputStream oStream = new FileOutputStream(new File(file, fileName));// 可以放UUName
		IOUtils.copy(inputStream, oStream);
		inputStream.close();
		oStream.close();
	}

	/**
	 * 通过标题进行模糊查询的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void findDocumentByLike(Download download, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Download> list = downLoadService.findDownLoadByTitleLike(download);

			request.setAttribute("list", list);

			jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);
		} catch (Exception e) {
			System.out.println("模糊查询方法异常" + e.getMessage());
		}

	}

	/**
	 * 删除文档的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void removeDocument(Download download, HttpServletRequest request, HttpServletResponse response) {
		try {
			downLoadService.deleteDownLoadById(download);

			jumpPage("/document?method=findDocumentPage", request, response);
		} catch (Exception e) {
			System.out.println("删除文档异常" + e.getMessage());
		}

	}

	/**
	 * 查询所有下载文档的方法
	 * 
	 * @param download
	 * @param request
	 * @param response
	 */
	private void findDocumentPage(Download download, HttpServletRequest request, HttpServletResponse response) {
		try {
			List<Download> list = downLoadService.findDownLoadAll();

			request.setAttribute("list", list);

			list = employList(list);

			jumpPage("/WEB-INF/jsp/document/document.jsp", request, response);

		} catch (Exception e) {
			System.out.println("获取所有下载文档异常" + e.getMessage());
		}

	}

	/**
	 * 继续封装
	 * 
	 * @param list
	 * @return
	 */
	private List<Download> employList(List<Download> list) {
		ArrayList<Download> newList = new ArrayList<>();
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
			System.out.println("跳转页面失败" + e.getMessage());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
