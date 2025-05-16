package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	//検索をする
	private String baseSql =
		    "SELECT t.*, s.ent_year, s.name " +
		    "FROM test t " +
		    "JOIN student s ON t.student_no = s.no " +
		    "WHERE s.ent_year=? AND t.class_num=? AND t.subject_cd=? AND t.school_cd=?";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
	    List<TestListSubject> testList = new ArrayList<>();
	    try {
	        while (rSet.next()) {
	            TestListSubject test = new TestListSubject();

	            // Studentオブジェクト作成・セット
	            Student student = new Student();
	            student.setNo(rSet.getString("student_no"));
	            student.setName(rSet.getString("name")); // 結合している場合
	            student.setEntYear(rSet.getInt("ent_year"));

	            test.setSubjectCd(rSet.getString("subject_cd"));
	            test.setPoint(rSet.getInt("point"));
	            test.setNum(rSet.getInt("no")); // 回数が「no」であると仮定

	            testList.add(test);
	        }
	    } catch (SQLException | NullPointerException e) {
	        e.printStackTrace();
	    }
	    return testList;
	}


	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		String order = " ORDER BY student_no ASC";

		try {
			statement = connection.prepareStatement(baseSql + order);
			statement.setInt(1, entYear);
			statement.setString(2, classNum);
			statement.setString(3, subject.getCd());
			statement.setString(4, school.getCd());

			// クエリ実行・結果取得
			rSet = statement.executeQuery();

			list = postFilter(rSet);

		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }
			if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }
		}

		return list;
	}
}
