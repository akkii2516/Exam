package scoremanager.main;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
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
//		boolean isAttend = false;//在学生フラグ
		List<Student> students = null;//学生リスト
		LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得
		int year = todaysDate.getYear();//現在の年を取得
		StudentDao sDao = new StudentDao();//学生Dao
		ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
		Map<String, String> errors = new HashMap<>();//エラーメッセージ

		//リクエストパラメーターの取得 2
		entYearStr = req.getParameter("f1");
		classNum = req.getParameter("f2");
		isAttendStr = req.getParameter("f3");

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
//		//リストを初期化
//		List<Integer> entYearSet = new ArrayList<>();
//		//10年前から1年後まで年をリストに追加
//		for (int i = year - 10; i < year + 1; i++) {
//			entYearSet.add(i);
//		}


		//レスポンス値をセット6
		//リクエストに入学年度をセット
		req.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		req.setAttribute("f2", classNum);
		//リクエストに学生リストをセット
		req.setAttribute("students", students);
		//リクエストにデータをセット
		req.setAttribute("class_num_set", list);
		req.setAttribute("ent_year_set", entYearSet);

		//JSPへフォワード 7
		req.getRequestDispatcher("student_list.jsp").forward(req, res);
    }
}
