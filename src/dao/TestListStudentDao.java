package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.Subject;
import bean.Test;
import bean.TestListStudent;
import bean.TestListSubject;

public class TestListStudentDao extends Dao {
	//学生番号から成績を出せばいいと思う
	private String baseSql = "select* from test where student_no=?";

	private postFilter














	public List<TestListStudent> filter(Student student)throws Exception{
		//リストを初期化
		List<TestListStudent> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;

		//SQL文のソート
		String order = " order by cd asc";
		try {
			statement = connection.prepareStatement(baseSql + order);
			statement.setString(1, student.getNo());
			rSet = statement.executeQuery();

			while (rSet.next()) {
				// 教科情報（Subject）を作成
				Subject subject = new Subject();
				subject.setCd(rSet.getString("cd"));

				// テスト成績データ（TestListStudent）を作成
				TestListStudent testListStudent = new TestListStudent();
				TestListSubject testListSubject = new TestListSubject();
				Test test = new Test();
				testListStudent.setStudentNo(student);
				testListSubject.setSubjectCd(subject);
				test.setPoint(rSet.getInt("point"));
				list.add(testListStudent);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			//プリペアードステートメントを閉じる
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
		}
		return list;

	}
}


