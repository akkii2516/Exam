package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	//検索をする
	private String baseSql = "SELECT * FROM test WHERE ent_year=? AND class_num=? AND subject_cd=? AND school_cd=?";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		List<TestListSubject> testList = new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListSubject subject = new TestListSubject();

				//取得する
				subject.setSubjectCd(rSet.getString("SUBJECT_CD"));
				subject.setSubjectName(rSet.getString("SUBJECT_NAME"));
				subject.setNum(rSet.getInt("NUM"));
				subject.setPoint(rSet.getInt("POINT"));

				testList.add(subject);
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
