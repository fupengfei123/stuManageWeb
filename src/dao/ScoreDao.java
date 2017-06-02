package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Banji;
import entity.Score;
import entity.Student;
import entity.Subject;

public class ScoreDao extends Jdbc {
	
	public List<Score> searchByCondition(Score condition, int begin) {
		List<Score> list = new ArrayList<Score>();
		createConnection();
		try {
			statement = connection.createStatement();
			String where = " where 1=1 ";
			if (condition.getStu().getName()!=null&&!condition.getStu().getName().equals("") ) {
				where += " and stuName like '%"+ condition.getStu().getName() + "'";
			}
			if (condition.getStu().getBj().getId()!=-1) {
				where += " and bjId="+ condition.getStu().getBj().getId();
			}
			if (condition.getSub().getId()!=-1) {
				where += " and subId="+ condition.getSub().getId();
			}
			String sql = "SELECT * from v_stu_sub_score "+ where + " order by stuId limit " + begin + ",4;";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Score sc = new Score();
				sc.setId(resultSet.getInt("scId"));
				sc.setScore(resultSet.getInt("score"));
				
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("bjId"));
				bj.setName(resultSet.getString("bjName"));
				
				Student stu = new Student();
				stu.setId(resultSet.getInt("stuId"));
				stu.setName(resultSet.getString("stuName"));
				stu.setBj(bj);
				sc.setStu(stu);
				
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("subId"));
				sub.setName(resultSet.getString("subName"));
				sc.setSub(sub);
				
				list.add(sc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Student> search(int begin) {
		List<Student> list = new ArrayList<Student>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select count(id) as n from v_stu_sub_score " + begin + ",4";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student stu = new Student();
				stu.setId(resultSet.getInt("id"));
				stu.setName(resultSet.getString("name"));
				stu.setAge(resultSet.getInt("age"));
				stu.setGender(resultSet.getString("gender"));
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("bj_id"));
				bj.setName(resultSet.getString("bjName"));
				stu.setBj(bj);
				list.add(stu);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int searchCount(Score condition) {
		int count = 0;
		createConnection();
		String where = " where 1=1 ";
		if (condition.getStu().getName()!=null && !condition.getStu().getName().equals("") ) {
			where += " and stuName like '%"+ condition.getStu().getName() + "'";
		}
		if (condition.getStu().getBj().getId()!=-1) {
			where += " and bjId="+ condition.getStu().getBj().getId();
		}
		if (condition.getSub().getId()!=-1) {
			where += " and subId="+ condition.getSub().getId();
		}
		try {
			statement = connection.createStatement();
			String sql = "select count(*) as n from v_stu_sub_score " + where;
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				count = resultSet.getInt("n");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return count;
	}
	
	public int add(Score sc) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "insert into score(stu_id,sub_id,score) values(?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setInt(1, sc.getStu().getId());
			preparedStatement.setInt(2, sc.getSub().getId());
			preparedStatement.setInt(3, sc.getScore());
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public int update(Score sc) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "update score set score=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setInt(1, sc.getScore());
			preparedStatement.setInt(2, sc.getId());
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
	
	public boolean modify(int id, String name, int age, String gender) {
		boolean flag = false;
		// 加载驱动
		createConnection();
		try {
			String sql = "update student set name='" + name + "', age='" + age
					+ "', gender='" + gender + "' where id='" + id + "';";
			preparedStatement = connection.prepareStatement(sql);
			// 返回结果
			int result = preparedStatement.executeUpdate();
			if (result > 0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return flag;
	}
}
