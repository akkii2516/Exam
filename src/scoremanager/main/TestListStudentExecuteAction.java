package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.TestListStudent;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestListStudentDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListStudentExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	// セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

	       // リクエストパラメータを取得
        String entYearStr = req.getParameter("f1");//入学年度
        String classNum = req.getParameter("f2");//クラス
        String subjectcd = req.getParameter("f3");//科目

        //Daoの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
        TestListSubjectDao testlistsubjectDao = new TestListSubjectDao();


        //現在の日時取得
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        //入学年度一覧
        List<Integer> entYearList = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) {
            entYearList.add(i);
        }
        Subject subjects = subjectDao.get(subjectcd, teacher.getSchool());

        //クラス一覧
        List<String> cNumlist = cNumDao.filter(teacher.getSchool());
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

		// セレクトボックスの表示用データセット
        req.setAttribute("f1", entYearList);
        req.setAttribute("f2", cNumlist);
        req.setAttribute("f3", subjectList);

        req.setAttribute("subjects", subjects);
        req.setAttribute("entYearStr", entYearStr);
        req.setAttribute("classNum", classNum);
		// リクエスト属性に設定
		req.setAttribute("student", student);
		req.setAttribute("testList", tes);
		req.getRequestDispatcher("test_list_student.jsp").forward(req, res);
	}
}
