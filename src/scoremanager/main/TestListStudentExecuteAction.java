package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.TestListStudent;
import dao.StudentDao;
import dao.TestListStudentDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// エラー格納用Map
		Map<String, String> errors = new HashMap<>();

		// パラメータ取得
		String studentNo = req.getParameter("studentNo");

		// 入力チェック
		if (studentNo == null || studentNo.isEmpty()) {
			errors.put("studentNo", "このフィールドを入力してください。");
			req.setAttribute("errors", errors); // JSPに渡す
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return; // これがないと後続が実行されてしまう
		}

		// 学生情報の取得
		StudentDao studentDao = new StudentDao();
		Student student = studentDao.get(studentNo);

		// 学生が存在しない場合のチェック
		if (student == null) {
			errors.put("studentNo", "成績情報が存在しませんでした。");
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
			return;
		}

		// テスト一覧の取得
		TestListStudentDao testListStudentDao = new TestListStudentDao();
		List<TestListStudent> tes = testListStudentDao.filter(student);

		// リクエスト属性に設定
		req.setAttribute("student", student);
		req.setAttribute("testList", tes);
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
