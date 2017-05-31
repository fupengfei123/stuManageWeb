package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Subject;

public class SubjectDao extends Jdbc {

	public List<Subject> search() {
		List<Subject> list = new ArrayList<Subject>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select * from subject";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Subject> search(int begin) {
		List<Subject> list = new ArrayList<Subject>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select * from subject as sub limit " + begin + ",4";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public Subject searchById(int id) {
		Subject sub = null;
		createConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select * from subject where id=" + id);
			while (resultSet.next()) {
				sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return sub;
	}

	public int searchCount() {
		int count = 0;
		createConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select count(id) as n from subject");
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

	public int add(Subject sub) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "insert into subject(name) values(?)";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setString(1, sub.getName());
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public boolean modify(Subject sub) {
		boolean flag = false;
		// 加载驱动
		createConnection();
		try {
			String sql = "update subject set name=? where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, sub.getName());
			preparedStatement.setInt(2, sub.getId());
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

	public int delete(Subject sub) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "delete from subject where id='" + sub.getId() + "';";
			preparedStatement = connection.prepareStatement(sql);
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public List<Subject> searchByCondition(Subject condition, int begin) {
		List<Subject> list = new ArrayList<Subject>();
		createConnection();
		try {
			statement = connection.createStatement();
			String where = "where 1=1";
			if (condition.getName() != null && !condition.getName().equals("")) {
				where += " and sub.name='" + condition.getName() + "'";
			}
			String sql = "select * from subject as sub " + where + " limit "
					+ begin + ",4;";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Subject> searchByBanji(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "SELECT sub.id,sub.name FROM banji AS bj"
					+ " INNER JOIN m_bj_sub AS m ON bj.id=m.bj_id"
					+ " INNER JOIN subject AS sub ON m.sub_id=sub.id"
					+ " WHERE bj.id=" + bjId;
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Subject> searchByNoBanji(int bjId) {
		List<Subject> list = new ArrayList<Subject>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "SELECT * FROM subject where id NOT IN(SELECT sub.id FROM banji AS bj"
					+ " INNER JOIN m_bj_sub AS m ON bj.id=m.bj_id"
					+ " INNER JOIN subject AS sub ON m.sub_id=sub.id"
					+ " WHERE bj.id=" + bjId + ")";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Subject sub = new Subject();
				sub.setId(resultSet.getInt("id"));
				sub.setName(resultSet.getString("name"));
				list.add(sub);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public int addBanjiAndSubject(int bjId, int subId) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "insert into m_bj_sub values(?,?)";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setInt(1, bjId);
			preparedStatement.setInt(2, subId);
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
		
	}

	public int deleteBanjiAndSubject(int bjId, int subId) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "delete from m_bj_sub where bj_Id=? and sub_Id=?";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setInt(1, bjId);
			preparedStatement.setInt(2, subId);
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}
}
