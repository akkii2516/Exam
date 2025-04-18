package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.security.auth.Subject;

import bean.School;

public class SubjectDao extends Dao {

	public Subject get(String cd,School school) throws Exception {
		//学生インスタンスを初期化
		Subject subject = new Subject();
		//データベースへのコネクションを確立
		Connection connection = getConnection();
		//プリペアードステートメント
		PreparedStatement statement = null;

		try {
			//プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement("select * from subject where cd=?");
			//プリペアードステートメントに学生番号をバインド
			statement.setString(1, cd);
			System.out.println(cd);
			//プリペアードステートメントを実行
			ResultSet rSet = statement.executeQuery();

			//学校Daoを初期化
			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {
				//リザルトセットが存在する場合
				//科目インスタンスに検索結果をセット
				subject.setCd(rSet.getString("cd"));
			} else {
				//リザルトセットが存在しない場合
				//学生インスタンスにnullをセット
				subject = null;
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

		return subject;
	}






}
