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
import bean.Test;

public class TestDao extends Dao {

	private String baseSql = "SELECT STUDENT.ENT_YEAR, STUDENT.CLASS_NUM, STUDENT.NO AS STUDENT_no, STUDENT.NAME, TEST.POINT,TEST.SUBJECT_cd FROM STUDENT left JOIN (select * from TEST where TEST.SUBJECT_CD = ? AND TEST.NO = ? ) as TEST ON STUDENT.NO = TEST.STUDENT_NO AND STUDENT.SCHOOL_CD = TEST.SCHOOL_CD WHERE STUDENT.ENT_YEAR = ? AND STUDENT.CLASS_NUM = ? AND STUDENT.SCHOOL_CD = ?";
	public Test get(Student student, Subject subject, School school, int no) throws Exception {
		//得点インスタンスを初期化
		Test test = new Test();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(
				    "SELECT * FROM TEST WHERE student_no=? AND subject_cd=? AND school_cd=? AND no=?");
				statement.setString(1, student.getNo());
				statement.setString(2, subject.getCd());
				statement.setString(3, school.getCd());
				statement.setInt(4, no);

			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学生Daoを初期化
			StudentDao studentDao = new StudentDao();
			//科目Daoを初期化
			SubjectDao subjectDao = new SubjectDao();
			//学校Danを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//学生インスタンスに検索結果をセット
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
				test.setSchool(schoolDao.get(rSet.getString("school_cd")));
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
			} else {
				//リザルトセットが存在しない場合
				//得点インスタンスにnullをセット
				test = null;
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

		return test;
	}

	//多分間違ってる
	private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
		//リストを初期化
		List<Test> list = new ArrayList<>();
		try {
			//リザルトセットを全権捜査
			while (rSet.next()) {
				//得点インスタンスを初期化
				Test test = new Test();

				//学生Daoを初期化
				StudentDao studentDao = new StudentDao();
				//科目Daoを初期化
				SubjectDao subjectDao = new SubjectDao();

				//得点インスタンスに検索結果をセット
				test.setStudent(studentDao.get(rSet.getString("student_no")));
				test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
				test.setSchool(school);
				test.setNo(rSet.getInt("no"));
				test.setPoint(rSet.getInt("point"));
				test.setClassNum(rSet.getString("class_num"));
				//リストに追加
				list.add(test);
			}
		} catch (SQLException | NullPointerException e) {
			e.printStackTrace();
		}

		return list;
	}

	//恐らく間違えてる
	public List<Test> filter(int entYear, String classNum, Subject subject ,int num,School school) throws Exception {
		//リストを初期化
		List<Test> list = new ArrayList<>();
		//コネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;
		//リザルトセット
		ResultSet rSet = null;
		//SQL文の条件
		String order = "order by student_no asc";


		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + order);
			//プリペアードステートメントに科目をバインド
			statement.setString(1, subject.getCd());
			//プリペアードステートメントにnumをバインド
			statement.setInt(2, num);
			//プリペアードステートメントにクラス番号をバインド
			statement.setInt(3, entYear);

			//プリペアードステートメントに入学年度をバインド
			statement.setString(4, classNum);
			//プリペアードステートメントに学校コードをバインド
			statement.setString(5,  school.getCd());
			//プリペアードステートメントを実行
			rSet = statement.executeQuery();
			//リストへの格納処理を実行
			list = postFilter(rSet, school);
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


	//絶対間違えてる
	public boolean save(List<Test> list) throws Exception {
	    Connection connection = getConnection();
	    int count = 0;

	    try {
	        connection.setAutoCommit(false); // トランザクション開始

	        for (Test test : list) {
	            Test old = get(test.getStudent(), test.getsubject(), test.getSchool(), test.getNo());

	            PreparedStatement statement;
	            if (old == null) {
	                // INSERT
	                statement = connection.prepareStatement(
	                    "INSERT INTO test (student_no, subject_cd, school_cd, no, point, class_num) VALUES (?, ?, ?, ?, ?, ?)");
	                statement.setString(1, test.getStudent().getNo());
	                statement.setString(2, test.getsubject().getCd());
	                statement.setString(3, test.getSchool().getCd());
	                statement.setInt(4, test.getNo());
	                statement.setInt(5, test.getPoint());
	                statement.setString(6, test.getClassNum());
	            } else {
	                // UPDATE
	                statement = connection.prepareStatement(
	                    "UPDATE test SET point = ?, class_num = ? WHERE student_no = ? AND subject_cd = ? AND school_cd = ? AND no = ?");
	                statement.setInt(1, test.getPoint());
	                statement.setString(2, test.getClassNum());
	                statement.setString(3, test.getStudent().getNo());
	                statement.setString(4, test.getsubject().getCd());
	                statement.setString(5, test.getSchool().getCd());
	                statement.setInt(6, test.getNo());
	            }

	            count += statement.executeUpdate();
	            statement.close(); // 各ループで確実にクローズ
	        }

	        connection.commit(); // トランザクションコミット
	    } catch (Exception e) {
	        connection.rollback(); // 失敗時はロールバック
	        throw e;
	    } finally {
	        if (connection != null) {
	            try {
	                connection.setAutoCommit(true);
	                connection.close();
	            } catch (SQLException sqle) {
	                throw sqle;
	            }
	        }
	    }

	    return count > 0;
	}


	private boolean save(Test test, Connection connection) throws Exception {
		return true;

	}
}