package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import util.RandomNumber;
import dao.UserDao;
import entity.User;

public class UserServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 解决乱码
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String type = request.getParameter("type");
		if (type != null) {
			if (type.equals("showLogin")) {
				showLogin(request, response);
			} else if (type.equals("doLogin")) {
				doLogin(request, response);
			} else if (type.equals("randomImage")) {
				randomImage(request, response);
			}
		} else {
			showLogin(request, response);
		}
	}

	private void doLogin(HttpServletRequest request,
			HttpServletResponse response) {
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
//		try {
			String rand = request.getParameter("random");
			String sRand = (String)request.getSession().getAttribute("rand");
			if (sRand!=null&&sRand.equals(rand)) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			UserDao ud = new UserDao();
			User user = ud.search(username, password);
			request.getSession().setAttribute("user",user);
			if (user != null) {
				out.print("success");
//				response.sendRedirect("main");
			} else {
				out.print("账号或密码错误！");
//				request.setAttribute("mes", "账号或密码错误！");
//				request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
			}
			} else {
				out.print("验证码错误！");
//				request.setAttribute("mes", "验证码错误！");
//				request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(request, response);
			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ServletException e) {
//			e.printStackTrace();
//		}
	}

	private void showLogin(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			request.getRequestDispatcher("WEB-INF/user/login.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void randomImage(HttpServletRequest request, HttpServletResponse response) {
		RandomNumber rn = new RandomNumber();
		try {
			String rand = rn.generateImage(response);
			request.getSession().setAttribute("rand", rand);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
