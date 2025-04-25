//そのうちやる
package dao;

import java.sql.ResultSet;
import java.util.List;

import bean.School;
import bean.TestListSubject;

public class TestListSubjectDao {

	private String baseSql = "select * from test where subject_cd = ?";

	private List<TestListSubject> postFilter(ResultSet rSet, School school) throws Exception {
		return null;

	}
}
