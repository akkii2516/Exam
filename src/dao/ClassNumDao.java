package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ClassNum;
import bean.School;

public class ClassNumDao extends Dao {

	public ClassNum get(String class_num, School school) throws Exception {

		//クラス番号インスタンスを初期化

		ClassNum classNum = new ClassNum();

		//データベースへのコネクションを確立

		Connection connection = getConnection();

		//プリペアードステートメント

		PreparedStatement statement = null;

		try {

			//プリペアードステートメントにSQL文をセット

			statement = connection.prepareStatement("select * from class_num where class_num = ? and school_cd = ?");

			//プリペアードステートメントに値をバインド

			statement.setString(1, class_num);

			statement.setString(2, school.getCd());

			//プリペアードステートメントを実行

			ResultSet rSet = statement.executeQuery();

			//学校Danを初期化

			SchoolDao sDao = new SchoolDao();

			if (rSet.next()) {

				//リザルトセットが存在する場合

				//クラス番号インスタンスに検索結果をセット

				classNum.setClass_num(rSet.getString("class_num"));

				classNum.setSchool(sDao.get(rSet.getString("school_cd")));

			} else {

				//リザルトセットが存在しない場合

				//学生インスタンスにnullをセット

				classNum = null;

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

		return classNum;

	}

	public List<String> filter(School school) throws Exception {

		//リストを初期化

		List<String> list = new ArrayList<>();

		//データベースへのコネクションを確立

		Connection connection = getConnection();

		//プリペアードステートメント

		PreparedStatement statement = null;

		try {

			//プリペアードステートメントにSQL文をセット

			statement = connection

					.prepareStatement("select class_num from class_num where school_cd=? order by class_num");

			//プリペアードステートメントに学校コードをバインド

			statement.setString(1, school.getCd());

			//プリペアードステートメントを実行

			ResultSet rSet = statement.executeQuery();

			//リザルトセットを全件捜査

			while (rSet.next()) {

				//リストにクラス番号を追加

				list.add(rSet.getString("class_num"));

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

	public boolean save(ClassNum classNum) throws Exception {

	    Connection connection = getConnection();

	    PreparedStatement statement = null;

	    int count = 0;

	    try {

	        // 既存のクラス番号を取得

	        ClassNum old = get(classNum.getClass_num(), classNum.getSchool());

	        if (old == null) {

	            // クラスが存在しない場合、INSERT文を実行

	            statement = connection.prepareStatement(

	                    "INSERT INTO class_num(school_cd, class_num) VALUES(?, ?)");

	            statement.setString(1, classNum.getSchool().getCd());

	            statement.setString(2, classNum.getClass_num());

	            count = statement.executeUpdate();

	        } else {

	            // クラスが既に存在する場合、UPDATE文を実行

	            statement = connection.prepareStatement(

	                    "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?");

	            statement.setString(1, classNum.getClass_num());

	            statement.setString(2, classNum.getClass_num());

	            statement.setString(3, classNum.getSchool().getCd());

	            count = statement.executeUpdate();

	        }

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

	    // 実行件数が1件以上あれば成功

	    return count > 0;

	}

	public boolean save(ClassNum classNum,String newClassNum) throws Exception {

		/**

		* クラス番号を更新する

		* @param classNum 現在のクラス情報（旧クラス番号と学校情報を含む）

		* @param newClassNum 新しいクラス番号

		* @return 更新成功ならtrue、対象が存在しないか失敗した場合はfalse

		*/

			Connection connection = getConnection();

			PreparedStatement statement = null;

			int count = 0;

			try {

				// 旧クラスが存在するか確認

				ClassNum old = get(classNum.getClass_num(), classNum.getSchool());

				if (old != null) {

					// 存在する場合、クラス番号を更新

					statement = connection.prepareStatement(

						"UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?"

					);

					statement.setString(1, newClassNum);

					statement.setString(2, classNum.getClass_num());

					statement.setString(3, classNum.getSchool().getCd());

					count = statement.executeUpdate();

				}

			} finally {

				if (statement != null) try { statement.close(); } catch (SQLException e) { throw e; }

				if (connection != null) try { connection.close(); } catch (SQLException e) { throw e; }

			}

			return count > 0;

		}

	public boolean update(ClassNum oldClassNum, String newClassNum) throws Exception {

	    Connection connection = getConnection();

	    PreparedStatement statement = null;

	    PreparedStatement studentStmt = null;

	    int count = 0;

	    int studentCount = 0;

	    try {

	        // 新しいクラス番号がすでに存在するか確認

	        ClassNum existingNew = get(newClassNum, oldClassNum.getSchool());

	        if (existingNew != null) {

	        	// studentテーブルのclass_numだけ更新

	            studentStmt = connection.prepareStatement(

	                "UPDATE student SET class_num = ? WHERE class_num = ? AND school_cd = ?"

	            );

	            studentStmt.setString(1, newClassNum);

	            studentStmt.setString(2, oldClassNum.getClass_num());

	            studentStmt.setString(3, oldClassNum.getSchool().getCd());

	            studentCount = studentStmt.executeUpdate();


	            System.out.println(studentCount);


	            System.out.println(studentCount);

	            connection.commit();

	            return true;


	        }

	        connection.setAutoCommit(false);

	        // クラス番号を更新

	        statement = connection.prepareStatement(

	            "UPDATE class_num SET class_num = ? WHERE class_num = ? AND school_cd = ?"

	        );

	        statement.setString(1, newClassNum);

	        statement.setString(2, oldClassNum.getClass_num());

	        statement.setString(3, oldClassNum.getSchool().getCd());

	        count = statement.executeUpdate();

	        // studentテーブルのクラス番号も更新

	        studentStmt = connection.prepareStatement(

	            "UPDATE student SET class_num = ? WHERE class_num = ? AND school_cd = ?"

	        );

	        studentStmt.setString(1, newClassNum);

	        studentStmt.setString(2, oldClassNum.getClass_num());

	        studentStmt.setString(3, oldClassNum.getSchool().getCd());

	        studentStmt.executeUpdate();

	        connection.commit();

	    } catch (Exception e) {

	        connection.rollback();

	        throw e;

	    } finally {

	        if (statement != null) try { statement.close(); } catch (SQLException e) {}

	        if (studentStmt != null) try { studentStmt.close(); } catch (SQLException e) {}

	        if (connection != null) try { connection.setAutoCommit(true); connection.close(); } catch (SQLException e) {}

	    }

	    return count > 0;

	}
	//削除できるといいな
	public boolean delete(String class_num, School school) throws Exception {
	    Connection connection = getConnection();
	    PreparedStatement checkStmt = null;
	    PreparedStatement deleteStmt = null;
	    ResultSet rs = null;

	    try {
	        System.out.println("[DEBUG] 削除処理開始: class_num=" + class_num + ", school_cd=" + school.getCd());

	        // 学生がいるか確認
	        checkStmt = connection.prepareStatement(
	            "SELECT COUNT(*) FROM student WHERE class_num = ? AND school_cd = ?"
	        );
	        checkStmt.setString(1, class_num);
	        checkStmt.setString(2, school.getCd());

	        rs = checkStmt.executeQuery();
	        if (rs.next()) {
	            int studentCount = rs.getInt(1);

	            System.out.println("学生数: " + studentCount);
	            if (studentCount > 0) {

	                System.out.println("学生が存在しています");
	                return false;
	            }
	        }

	        // 学生がいないので削除実行
	        deleteStmt = connection.prepareStatement(
	            "DELETE FROM class_num WHERE class_num = ? AND school_cd = ?"
	        );
	        deleteStmt.setString(1, class_num);
	        deleteStmt.setString(2, school.getCd());

	        int count = deleteStmt.executeUpdate();

	        return count > 0;

	    } catch (Exception e) {
	        e.printStackTrace();
	        throw e;

	    } finally {
	        if (rs != null) try { rs.close(); } catch (Exception e) {}
	        if (checkStmt != null) try { checkStmt.close(); } catch (Exception e) {}
	        if (deleteStmt != null) try { deleteStmt.close(); } catch (Exception e) {}
	        if (connection != null) try { connection.close(); } catch (Exception e) {}
	    }
	}


}
