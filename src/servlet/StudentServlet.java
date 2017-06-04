package servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import dao.BanjiDao;
import dao.StudentDao;
import entity.Banji;
import entity.Student;

@SuppressWarnings("serial")
public class StudentServlet extends HttpServlet {
	// StudentDao成员变量
	StudentDao sd = new StudentDao();
	BanjiDao bjDao = new BanjiDao();

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 解决乱码
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		// 传参调用方法
		String type = request.getParameter("type");
		if (type != null) {
			if (type.equals("show")) {
				show(request, response);
			} else if (type.equals("showAdd")) {
				showAdd(request, response);
			} else if (type.equals("add")) {
				add(request, response);
			} else if (type.equals("showModify")) {
				showModify(request, response);
			} else if (type.equals("modify")) {
				modify(request, response);
			} else if (type.equals("delete")) {
				delete(request, response);
			} else if (type.equals("search")) {
				search(request, response);
			}
		} else {
			search(request, response);
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		int age = -1;
		if (request.getParameter("age") != null
				&& !request.getParameter("age").equals("")) {
			try {
				age = Integer.parseInt(request.getParameter("age"));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
		}
		int bjId = -1;
		if (request.getParameter("banji") != null) {
			bjId = Integer.parseInt(request.getParameter("banji"));
		}
		Student condition = new Student();
		condition.setName(name);
		condition.setGender(gender);
		condition.setAge(age);

		Banji conditionBj = new Banji();
		conditionBj.setId(bjId);
		condition.setBj(conditionBj);

		List<Student> list = sd.searchByCondition(condition, 0);
		List<Banji> bjList = bjDao.search();
		request.setAttribute("page", 1);
		request.setAttribute("maxPage", 5);
		request.setAttribute("stus", list);
		request.setAttribute("bjs", bjList);
		request.setAttribute("condition", condition);
		try {
			request.getRequestDispatcher("WEB-INF/student/student.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student stu = new Student();
		stu.setId(id);
		int a = sd.delete(stu);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (a > 0) {
				out.print("<script>alert('删除成功！');window.location.href='student?type=show';</script>");
			} else {
				out.print("<script>alert('删除失败！');window.location.href='student?type=show';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Student stu = sd.searchById(id);
		List<Banji> bjList = bjDao.search();
		request.setAttribute("stu", stu);
		request.setAttribute("bjs", bjList);
		try {
			request.getRequestDispatcher("WEB-INF/student/modify.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		List<Banji> bjList = bjDao.search();
		request.setAttribute("bjs", bjList);
		try {
			request.getRequestDispatcher("WEB-INF/student/add.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		try {
			String name = "";
			int age = 0;
			String gender = "";
			int bjId = 0;
			String fileName = "";
			String uploadPath = request.getSession().getServletContext()
					.getRealPath("/")
					+ "/photo";
			FileItemFactory factory = new DiskFileItemFactory();// 为该请求创建一个DiskFileItemFactory对象，通过它来解析请求。执行解析后，所有的表单项目都保存在一个List中。
			ServletFileUpload upload = new ServletFileUpload(factory);
			List<FileItem> items = upload.parseRequest(request);
			for (FileItem item : items) {
				String fieldName = item.getFieldName();
				if (fieldName != null) {
					if (fieldName.equals("photo")) {
						fileName = item.getName();
						
						int index = fileName.lastIndexOf(".");
						UUID uid = UUID.randomUUID();
						fileName = uid + fileName.substring(index);

						String url = uploadPath + "/" + fileName;
						File savedFile = new File(url);
						item.write(savedFile);
					} else if (fieldName.equals("name")) {
						name = new String(item.getString().getBytes(
								"ISO-8859-1"), "utf-8");
					} else if (fieldName.equals("age")) {
						age = Integer.parseInt(item.getString());
					} else if (fieldName.equals("gender")) {
						gender = new String(item.getString().getBytes(
								"ISO-8859-1"), "utf-8");
					} else if (fieldName.equals("banji")) {
						bjId = Integer.parseInt(item.getString());
					}
				}
			}
			Student stu = new Student();
			stu.setName(name);
			stu.setAge(age);
			stu.setGender(gender);
			stu.setPhoto(fileName);
			Banji bj = new Banji();
			bj.setId(bjId);
			stu.setBj(bj);
			sd.add(stu);
			// 添加保存成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			out.print("<script>alert('保存成功！');window.location.href='student?type=show';</script>");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		String gender = request.getParameter("gender");
		int bjId = Integer.parseInt(request.getParameter("banji"));
		Student stu = new Student();
		stu.setId(id);
		stu.setName(name);
		stu.setAge(age);
		stu.setGender(gender);
		Banji bj = new Banji();
		bj.setId(bjId);
		stu.setBj(bj);
		sd.update(stu);
		boolean flag = sd.modify(id, name, age, gender);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (flag) {
				out.print("<script>alert('修改成功！');window.location.href='student?type=show';</script>");

			} else {
				out.print("<script>alert('修改失败！');window.location.href='student?type=show';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			int page = 1;
			int count = sd.searchCount();
			int maxPage = (count - 1) / 4 + 1;
			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
				if (page < 1) {
					page = 1;
				}
				if (page > maxPage) {
					page = maxPage;
				}
			}
			int begin = (page - 1) * 4;

			List<Student> list = sd.search(begin);
			List<Banji> bjList = bjDao.search();
			request.setAttribute("stus", list);
			request.setAttribute("bjs", bjList);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.getRequestDispatcher("WEB-INF/student/student.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
