package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MainServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		try {
			if (type == null) {
				request.getRequestDispatcher("WEB-INF/main/index.jsp").forward(
						request, response);
			} else if (type.equals("header")) {
				request.getRequestDispatcher("WEB-INF/main/header.jsp")
						.forward(request, response);

			} else if (type.equals("footer")) {
				request.getRequestDispatcher("WEB-INF/main/footer.jsp")
						.forward(request, response);
			} else if (type.equals("left")) {
				request.getRequestDispatcher("WEB-INF/main/left.jsp")
				.forward(request, response);
			}
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
