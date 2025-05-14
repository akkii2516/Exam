//package scoremanager.main;
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.Teacher;
//import dao.ClassNumDao;
//import dao.TeacherDao;
//import tool.Action;
//
//public class TeacherUpdateAction extends Action {
//
//	@Override
//	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//
//		HttpSession session = req.getSession();//セッション
//		Teacher teacher = (Teacher)session.getAttribute("user");
//
//		//student_list.jspから変更を押した学生番号を取得
//		String id = req.getParameter("id");
//
//
//
//		//学生Daoを初期化
//		TeacherDao TeacherDao = new TeacherDao();
//		//学生番号をもとに学生の情報を取得
//		Teacher teacherid = TeacherDao.get(id);
//
//		//クラス番号Daoを初期化
//		ClassNumDao cNumDao = new ClassNumDao();
//		//ログインユーザーの学校コードをもとにクラス番号の一覧を取得
//		List<String> list = cNumDao.filter(teacher.getSchool());
//
//		//リクエストにデータをセット
//		req.setAttribute("id", id);
//
//		//指定されたJSPページ("student_update.jsp")へフォワード
//		req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
//	}
//}






package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import dao.TeacherDao;
import tool.Action;
public class TeacherUpdateAction extends Action {
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		HttpSession session = req.getSession(); // セッション取得
		Teacher loginUser = (Teacher)session.getAttribute("user"); // ログインユーザー取得
		// teacher_list.jsp などから渡されたIDを取得
		String id = req.getParameter("id");
		// 教員Daoを初期化して対象教員を取得
		TeacherDao teacherDao = new TeacherDao();
		Teacher targetTeacher = teacherDao.get(id);
		// クラス番号一覧を取得（学校コードに基づく）
		ClassNumDao cNumDao = new ClassNumDao();
		List<String> classNumList = cNumDao.filter(loginUser.getSchool());
		// リクエストにデータをセット
		req.setAttribute("id", id); // 元のID
		req.setAttribute("teacher", targetTeacher); // 教員情報
		req.setAttribute("classNumList", classNumList); // （使うならJSP側で）
		// JSPへフォワード
		req.getRequestDispatcher("teacher_update.jsp").forward(req, res);
	}
}