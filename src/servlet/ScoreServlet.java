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
import dao.ScoreDao;
import dao.StudentDao;
import dao.SubjectDao;
import entity.Banji;
import entity.Score;
import entity.Student;
import entity.Subject;

@SuppressWarnings("serial")
public class ScoreServlet extends HttpServlet {
	ScoreDao scoreDao = new ScoreDao();
	BanjiDao bjDao = new BanjiDao();
	SubjectDao subDao = new SubjectDao();
	StudentDao stuDao = new StudentDao();

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
			if (type.equals("search")) {
				search(request, response);
			} else if (type.equals("showInput")) {
				showInput(request, response);
			} else if (type.equals("input")) {
				input(request, response);
			}
		} else {
			search(request, response);
		}
	}

	private void showInput(HttpServletRequest request,
			HttpServletResponse response) {
		String name = "";
		int bjId = -1;
		int subId = -1;
		if (request.getParameter("name") != null
				&& !request.getParameter("name").equals("")) {
			name = request.getParameter("name");
		}
		if (request.getParameter("banji") != null) {
			bjId = Integer.parseInt(request.getParameter("banji"));
		}
		if (request.getParameter("subject") != null) {
			subId = Integer.parseInt(request.getParameter("subject"));
		}

		Score condition = new Score();
		Student conditionStu = new Student();
		conditionStu.setName(name);

		Banji conditionBj = new Banji();
		conditionBj.setId(bjId);
		conditionStu.setBj(conditionBj);

		Subject conditionSub = new Subject();
		conditionSub.setId(subId);

		condition.setStu(conditionStu);
		condition.setSub(conditionSub);

		int page = 1;
		int max = scoreDao.searchCount(condition);
		int maxPage = (max - 1) / 4 + 1;
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
		List<Banji> bjList = bjDao.search();
		List<Score> scList = scoreDao.searchByCondition(condition, begin);
		List<Subject> subList = subDao.search();
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("scoreList", scList);
		request.setAttribute("bjList", bjList);
		request.setAttribute("subList", subList);
		request.setAttribute("condition", condition);
		try {
			request.getRequestDispatcher("WEB-INF/score/showInput.jsp")
					.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void search(HttpServletRequest request, HttpServletResponse response) {
		String name = "";
		int bjId = -1;
		int subId = -1;
		if (request.getParameter("name") != null
				&& !request.getParameter("name").equals("")) {
			name = request.getParameter("name");
		}
		if (request.getParameter("banji") != null) {
			bjId = Integer.parseInt(request.getParameter("banji"));
		}
		if (request.getParameter("subject") != null) {
			subId = Integer.parseInt(request.getParameter("subject"));
		}

		Score condition = new Score();
		Student conditionStu = new Student();
		conditionStu.setName(name);

		Banji conditionBj = new Banji();
		conditionBj.setId(bjId);
		conditionStu.setBj(conditionBj);

		Subject conditionSub = new Subject();
		conditionSub.setId(subId);

		condition.setStu(conditionStu);
		condition.setSub(conditionSub);

		int page = 1;
		// int count = scoreDao.searchCount(condition);
		int max = scoreDao.searchCount(condition);
		int maxPage = (max - 1) / 4 + 1;
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
		List<Banji> bjList = bjDao.search();
		List<Score> scList = scoreDao.searchByCondition(condition, begin);
		List<Subject> subList = subDao.search();
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("scoreList", scList);
		request.setAttribute("bjList", bjList);
		request.setAttribute("subList", subList);
		request.setAttribute("condition", condition);
		try {
			request.getRequestDispatcher("WEB-INF/score/score.jsp").forward(
					request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private int input(HttpServletRequest request, HttpServletResponse response) {
		// 获取参数
		int scId = 0;
		if (request.getParameter("scoreId") != null) {
			scId = Integer.parseInt(request.getParameter("scoreId"));
		}
		int stuId = 0;
		if (request.getParameter("stuId") != null) {
			stuId = Integer.parseInt(request.getParameter("stuId"));
		}
		int subId = 0;
		if (request.getParameter("subId") != null) {
			subId = Integer.parseInt(request.getParameter("subId"));
		}
		int score = 0;
		if (request.getParameter("score") != null) {
			score = Integer.parseInt(request.getParameter("score"));
		}
		Score sc = new Score();
		sc.setScore(score);
		sc.setId(scId);
		Student stu = new Student();
		stu.setId(stuId);
		Subject sub = new Subject();
		sub.setId(subId);
		sc.setStu(stu);
		sc.setSub(sub);
		int result = 0;
		if (scId == 0) {
			result = scoreDao.add(sc);
		} else {
			result = scoreDao.update(sc);
		}
		try {
			// 修改成功与否的提示并跳转到首页
			PrintWriter out = response.getWriter();
			if (result > 0) {
				out.print("保存成功！");
			} else {
				out.print("保存失败！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}
}
