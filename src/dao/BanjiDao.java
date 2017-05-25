package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Banji;

public class BanjiDao extends Jdbc {
	 
	public List<Banji> search() {
		List<Banji> list = new ArrayList<Banji>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select * from banji";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("id"));
				bj.setName(resultSet.getString("name"));
				list.add(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public List<Banji> search(int begin) {
		List<Banji> list = new ArrayList<Banji>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select * from banji as bj limit "+ begin + ",4";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("id"));
				bj.setName(resultSet.getString("name"));
				list.add(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}

	public Banji searchById(int id) {
		Banji bj = null;
		createConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select * from banji where id=" + id);
			while (resultSet.next()) {
				bj = new Banji();
				bj.setId(resultSet.getInt("id"));
				bj.setName(resultSet.getString("name"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return bj;
	}

	public int searchCount() {
		int count = 0;
		createConnection();
		try {
			statement = connection.createStatement();
			resultSet = statement
					.executeQuery("select count(id) as n from banji");
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

	public int add(Banji bj) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "insert into banji(name) values(?)";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setString(1, bj.getName());
			// 返回结果
			result = preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return result;
	}

	public boolean modify(Banji bj) {
		boolean flag = false;
		// 加载驱动
		createConnection();
		try {
			String sql = "update banji set name=? where id=?;";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, bj.getName());
			preparedStatement.setInt(2, bj.getId());
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

	public int delete(Banji bj) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "delete from banji where id='" + bj.getId() + "';";
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

	public List<Banji> searchByCondition(Banji condition, int begin) {
		List<Banji> list = new ArrayList<Banji>();
		createConnection();
		try {
			statement = connection.createStatement();
			String where = "where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and bj.name='" + condition.getName() + "'";
			}
			String sql = "select * from banji as bj "+ where +" limit " + begin + ",4;";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("id"));
				bj.setName(resultSet.getString("name"));
				list.add(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return list;
	}
}
