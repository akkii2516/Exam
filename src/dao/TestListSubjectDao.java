package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.TestListSubject;

public class TestListSubjectDao extends Dao {
	// 科目番号から成績を取得
	private String baseSql = "SELECT * FROM test WHERE subject_cd=?";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {
		List<TestListSubject> testlistSubject = new ArrayList<>();
		try {
			while (rSet.next()) {
				TestListSubject subject = new TestListSubject();

				// test テーブルに含まれる必要あり
				subject.setSubjectCd(rSet.getString("SUBJECT_CD"));
				subject.setSubjectName(rSet.getString("SUBJECT_NAME"));
				subject.setNum(rSet.getInt("NUM"));
				subject.setPoint(rSet.getInt("POINT"));

				testlistSubject.add(subject);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return testlistSubject;
	}

	// 科目番号で検索するメソッド
	public List<TestListSubject> filter(String subjectCd) throws Exception {
		List<TestListSubject> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		String order = " ORDER BY student_no ASC";  // 学生番号で並び替え（必要に応じて変更）
		try {
			statement = connection.prepareStatement(baseSql + order);
			statement.setString(1, subjectCd);
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
