package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Banji;
import entity.Student;

public class StudentDao extends Jdbc {

	public List<Student> search(int begin) {
		List<Student> list = new ArrayList<Student>();
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select s.*,bj.name as bjName from student as s left join banji as bj on s.bj_id=bj.id limit " + begin + ",4";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student stu = new Student();
				stu.setId(resultSet.getInt("id"));
				stu.setName(resultSet.getString("name"));
				stu.setAge(resultSet.getInt("age"));
				stu.setGender(resultSet.getString("gender"));
				stu.setPhoto(resultSet.getString("photo"));
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

	public Student searchById(int id) {
		Student stu = null;
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select * from student where id=" + id;
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				stu = new Student();
				stu.setId(resultSet.getInt("id"));
				stu.setName(resultSet.getString("name"));
				stu.setAge(resultSet.getInt("age"));
				stu.setGender(resultSet.getString("gender"));
				Banji bj = new Banji();
				bj.setId(resultSet.getInt("bj_id"));
				stu.setBj(bj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return stu;
	}

	public int searchCount() {
		int count = 0;
		createConnection();
		try {
			statement = connection.createStatement();
			String sql = "select count(id) as n from student";
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

	public int add(Student stu) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "insert into student(name,age,gender,bj_id,photo) values(?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setString(1, stu.getName());
			preparedStatement.setInt(2, stu.getAge());
			preparedStatement.setString(3, stu.getGender());
			preparedStatement.setInt(4, stu.getBj().getId());
			preparedStatement.setString(5, stu.getPhoto());
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

	public int delete(Student stu) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "delete from student where id='" + stu.getId() + "';";
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
	
	public List<Student> searchByCondition(Student condition, int begin) {
		List<Student> list = new ArrayList<Student>();
		createConnection();
		try {
			statement = connection.createStatement();
			String where = "where 1=1";
			if (condition.getName()!=null&&!condition.getName().equals("")) {
				where += " and s.name='" + condition.getName() + "'";
			}
			if (condition.getAge() != -1) {
				where += " and s.age="+condition.getAge();
			}
			if (condition.getGender()!=null&&!condition.getGender().equals("")) {
				where += " and s.gender='" + condition.getGender() + "'";
			}
			if (condition.getBj().getId() != -1) {
				where += " and s.bj_id="+condition.getBj().getId();
			}
			String sql = "select s.*,bj.name as bjName from student as s left join banji as bj on s.bj_id=bj.id "+ where + " limit " + begin + ",4;";
			resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				Student stu = new Student();
				stu.setId(resultSet.getInt("id"));
				stu.setName(resultSet.getString("name"));
				stu.setAge(resultSet.getInt("age"));
				stu.setGender(resultSet.getString("gender"));
				stu.setPhoto(resultSet.getString("photo"));
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

	public int update(Student stu) {
		int result = 0;
		// 加载驱动
		createConnection();
		try {
			String sql = "update student set name=?,age=?,gender=?,bj_id=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			// 给name,age,gender赋值
			preparedStatement.setString(1, stu.getName());
			preparedStatement.setInt(2, stu.getAge());
			preparedStatement.setString(3, stu.getGender());
			preparedStatement.setInt(4, stu.getBj().getId());
			preparedStatement.setInt(5, stu.getId());
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
