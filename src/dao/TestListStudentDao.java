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
import bean.TestListStudent;

public class TestListStudentDao extends Dao {
	//学生番号から成績を出せばいいと思う
	private String baseSql = "select* from test where student_no=?";

	private List<TestListStudent> postFilter(ResultSet rSet, School school) throws Exception {
		//リストを初期化
		List<TestListStudent> testlistStudent = new ArrayList<>();
		try {
			//リザルトセットを全権捜査
			while (rSet.next()) {
				//学生インスタンスを初期化
				TestListStudent student = new TestListStudent();
				//学生インスタンスに検索結果をセット
				student.setStudentNo(rSet.getString("STUDENT_NO"));
				student.setSubjectCd(rSet.getString("name"));
				//リストに追加
				testlistStudent.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return testlistStudent;
	}

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
				testListStudent.setStudentNo(rSet.getString("cd"));
				testListStudent.setSubjectCd(rSet.getString("cd"));
				test.setPoint(rSet.getInt("point"));
				list.add(testListStudent);
			}




//  	SUBJECT_CD  	SCHOOL_CD  	NO  	POINT  	CLASS_NUM

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


