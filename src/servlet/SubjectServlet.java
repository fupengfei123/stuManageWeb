package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import entity.Subject;

@SuppressWarnings("serial")
public class SubjectServlet extends HttpServlet {
	// SubjectDao成员变量
	SubjectDao subDao = new SubjectDao();
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
		Subject condition = new Subject();
		condition.setName(name);
		List<Subject> list = subDao.searchByCondition(condition,0);
		request.setAttribute("page", 1);
		request.setAttribute("maxPage", 5);
		request.setAttribute("subs", list);
		try {
			request.getRequestDispatcher("WEB-INF/subject/subject.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Subject sub = new Subject();
		sub.setId(id);
		int a = subDao.delete(sub);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (a>0) {
				out.print("<script>alert('删除成功！');window.location.href='subject?type=show';</script>");
			} else {
				out.print("<script>alert('删除失败！');window.location.href='subject?type=show';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Subject sub = subDao.searchById(id);
		request.setAttribute("sub", sub);
		try {
			request.getRequestDispatcher("WEB-INF/subject/modify.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showAdd(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/subject/add.jsp").forward(request,
					response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String name = request.getParameter("name");
		Subject sub = new Subject();
		sub.setName(name);
		subDao.add(sub);
		try {
			// 添加保存成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			out.print("<script>alert('保存成功！');window.location.href='subject?type=show';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		Subject sub = new Subject();
		sub.setId(id);
		sub.setName(name);
		boolean flag = subDao.modify(sub);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (flag) {
				out.print("<script>alert('修改成功！');window.location.href='subject?type=show';</script>");
				
			} else {
				out.print("<script>alert('修改失败！');window.location.href='subject?type=show';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		try {
			int page = 1;
			int count = subDao.searchCount();
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

			List<Subject> list = subDao.search(begin);

			request.setAttribute("subs", list);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.getRequestDispatcher("WEB-INF/subject/subject.jsp").forward(
					request, response);
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
