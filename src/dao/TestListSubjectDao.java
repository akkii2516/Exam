//studentをsubjectにかえておく
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
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
				//テストリストのインスタンスを初期化
				TestListStudent student = new TestListStudent();
				//テスト結果の検索結果をセット
				student.setStudentNo(rSet.getString("STUDENT_NO"));
				student.setStudentName(rSet.getString("STUDENT_NAME"));
				student.setClassNum(rSet.getString("CLASS_NUM"));

				//リストに追加
				testlistStudent.add(student);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return testlistStudent;
	}

	public List<TestListStudent> filter(Student student) throws Exception {
		List<TestListStudent> list = new ArrayList<>();
		Connection connection = getConnection();
		PreparedStatement statement = null;
		ResultSet rSet = null;

		String order = " order by subject_cd asc";
		try {
			statement = connection.prepareStatement(baseSql + order);
			statement.setString(1, student.getNo());
			rSet = statement.executeQuery();

			// 結果セットをリストに変換
			list = postFilter(rSet, null);

		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
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

