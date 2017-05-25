package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BanjiDao;
import entity.Banji;

@SuppressWarnings("serial")
public class BanjiServlet extends HttpServlet {
	// BanjiDao成员变量
	BanjiDao sd = new BanjiDao();
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
		Banji condition = new Banji();
		condition.setName(name);
		List<Banji> list = sd.searchByCondition(condition,0);
		request.setAttribute("page", 1);
		request.setAttribute("maxPage", 5);
		request.setAttribute("bjs", list);
		try {
			request.getRequestDispatcher("WEB-INF/banji/banji.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void delete(HttpServletRequest request, HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Banji bj = new Banji();
		bj.setId(id);
		int a = sd.delete(bj);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (a>0) {
				out.print("<script>alert('删除成功！');window.location.href='banji?type=show';</script>");
			} else {
				out.print("<script>alert('删除失败！');window.location.href='banji?type=show';</script>");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void showModify(HttpServletRequest request,
			HttpServletResponse response) {
		int id = Integer.parseInt(request.getParameter("id"));
		Banji bj = sd.searchById(id);
		request.setAttribute("bj", bj);
		try {
			request.getRequestDispatcher("WEB-INF/banji/modify.jsp").forward(request,
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
			request.getRequestDispatcher("WEB-INF/banji/add.jsp").forward(request,
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
		Banji bj = new Banji();
		bj.setName(name);
		sd.add(bj);
		try {
			// 添加保存成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			out.print("<script>alert('保存成功！');window.location.href='banji?type=show';</script>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void modify(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		String name = request.getParameter("name");
		int id = Integer.parseInt(request.getParameter("id"));
		Banji bj = new Banji();
		bj.setId(id);
		bj.setName(name);
		boolean flag = sd.modify(bj);
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (flag) {
				out.print("<script>alert('修改成功！');window.location.href='banji?type=show';</script>");
				
			} else {
				out.print("<script>alert('修改失败！');window.location.href='banji?type=show';</script>");
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

			List<Banji> list = sd.search(begin);

			request.setAttribute("bjs", list);
			request.setAttribute("page", page);
			request.setAttribute("maxPage", maxPage);
			request.getRequestDispatcher("WEB-INF/banji/banji.jsp").forward(
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
