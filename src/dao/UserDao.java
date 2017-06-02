package dao;

import java.sql.SQLException;

import entity.User;

public class UserDao extends Jdbc {
	public User search(String username,String password) {
		createConnection();
		User user = null;
		try {
			String sql = "select * from user where username=? and password=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				user = new User();
				user.setId(resultSet.getInt("id"));
				user.setUsername(resultSet.getString("username"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return user;
	}

}
