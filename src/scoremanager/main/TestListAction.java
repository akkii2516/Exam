
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
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import tool.Action;

public class TestListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー（教員）情報を取得

    	HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String entYearStr="";//入力された入学年度
		String classNum = "";//入力されたクラス番号
		String isAttendStr="";//入力された在学生フラグ
//		int entYear = 0;//入学年度
		boolean isAttend = false;//在学生フラグ
		List<Student> students = null;//学生リスト
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		SubjectDao subjectDao = new SubjectDao();
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメーターの取得 2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");
		//DBからデータ取得3
				List<String>cNumlist = cNumDao.filter(teacher.getSchool()); //クラス情報
				List<Subject>list = subjectDao.filter(teacher.getSchool()); //科目情報

		//在学フラグが送信されていた場合
		if (isAttendStr != null) {
			//在学フラグを立てる
			isAttend = true;
			//リクエストに在学フラグをセット
			req.setAttribute("f3", isAttendStr);
		}

//		//ビジネスロック 4
//		if (entYearStr != null) {
//			//数値に変換
//			entYear = Integer.parseInt(entYearStr);
//		}
		//リストを初期化
		List<Integer> entYearSet = new ArrayList<>();
		//10年前から1年後まで年をリストに追加
		for (int i = year - 10; i < year + 1; i++) {
			entYearSet.add(i);
		}


		//レスポンス値をセット6
		//リクエストに入学年度をセット
//		req.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		req.setAttribute("f2", cNumlist);
		req.setAttribute("f3", list);
		req.setAttribute("f1", entYearSet);
		//リクエストにデータをセット
//		req.setAttribute("class_num_set", list);
//		req.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード 7
		req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
//=======
//package scoremanager.main;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.Subject;
//import bean.Teacher;
//import bean.Test;
//import dao.SubjectDao;
//import dao.TestDao;
//import tool.Action;
//
//public class TestListAction extends Action {
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        // セッションからログイン中の教員情報を取得
//        HttpSession session = req.getSession();
//        Teacher teacher = (Teacher) session.getAttribute("user");
//
//        // パラメータ取得
//        String f1 = req.getParameter("f1"); // 入学年度
//        String f2 = req.getParameter("f2"); // クラス
//        String f3 = req.getParameter("f3"); // 科目コード
//        String f4 = req.getParameter("f4"); // 回数
//
//        // 各種リストの取得
//        SubjectDao subjectDao = new SubjectDao();
//        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
//
//        TestDao testDao = new TestDao();
//        List<Test> testList = testDao.search(f1, f2, f3, f4, teacher.getSchool());
//
//        // リクエストスコープにセット
//        req.setAttribute("subject_list", subjectList);
//        req.setAttribute("tests", testList);
//        req.setAttribute("f1", f1);
//        req.setAttribute("f2", f2);
//        req.setAttribute("f3", f3);
//        req.setAttribute("f4", f4);
//
//        // JSPにフォワード
//        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
//    }
//}
//>>>>>>> branch 'master' of https://github.com/akkii2516/Exam.git
