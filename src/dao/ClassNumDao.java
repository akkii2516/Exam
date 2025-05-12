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
		//コネクションを確立
				Connection connection = getConnection();
				//プリペアードステートメント
				PreparedStatement statement = null;
				//実行件数
				int count = 0;

				try {
					//データベースからクラスを取得
					ClassNum old = get(classNum.getClass_num(), classNum.getSchool());
					if (old == null) {
						//クラスが存在しなかった場合
						//プリペアードステートメントにINSERT文をセット
						statement = connection.prepareStatement(
								"insert into class_num(school_cd,class_num) values(?,?)");
						//プリペアードステートメントに値をバインド
						statement.setString(1, classNum.getSchool().getCd());
						statement.setString(2, classNum.getClass_num());
					} else {
						//クラスが存在した場合
						//プリペアードステートメントにUPDATE文をセット
						statement = connection
								.prepareStatement("update class_num set class_num=?");
						//プリペアードステートメントに値をバインド
						statement.setString(1, classNum.getClass_num());
					}

					//プリペアードステートメントを実行
					count = statement.executeUpdate();

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

				if (count > 0) {
					//実行件数が1件以上ある場合
					return true;
				} else {
					//実行件数が0件の場合
					return false;
				}
			}

	public boolean save(ClassNum classNum,String newClassNum) throws Exception {
		return true;
	}
}
