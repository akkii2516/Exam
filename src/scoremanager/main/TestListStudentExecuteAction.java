package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// パラメータ取得
		String entYear = request.getParameter("entYear");
		String classNum = request.getParameter("classNum");
		String studentNo = request.getParameter("studentNo");

		// 入力チェック（全てのパラメータが揃っていなければエラー）
		if (entYear == null || classNum == null || studentNo == null ||
			entYear.isEmpty() || classNum.isEmpty() || studentNo.isEmpty()) {
			request.setAttribute("message", "すべての検索条件を指定してください。");
			return;
		}

		// 学生情報の取得
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.get(studentNo);

		if (student == null) {
			request.setAttribute("message", "指定された学生は存在しません。");
			return;
		}

		// テスト一覧の取得
		TestListStudentDao testListStudentDao = new TestListStudentDao();
		List<TestListStudent> testList = testListStudentDao.filter(student);

		// リクエスト属性に設定
		request.setAttribute("student", student);
		request.setAttribute("testList", testList);
	}
}
