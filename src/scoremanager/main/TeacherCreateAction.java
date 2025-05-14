//package scoremanager.main;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.School;
//import bean.Teacher;
//import dao.TeacherDao;
//import tool.Action;
//
//public class TeacherCreateAction extends Action {
//    @Override
//    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//        req.setCharacterEncoding("UTF-8");
//
//        // セッションからログインユーザー取得（所属校用）
//        HttpSession session = req.getSession();
//        Teacher loginUser = (Teacher) session.getAttribute("user");
//        School school = loginUser.getSchool();
//
//        // 入力値取得
//        String id = req.getParameter("id");
//        String name = req.getParameter("name");
//        String password = req.getParameter("password");
//
//        // 教員オブジェクト作成
//        Teacher teacher = new Teacher();
//        teacher.setId(id);
//        teacher.setName(name);
//        teacher.setPassword(password);
//        teacher.setSchool(school);
//
//        // 登録処理
//        TeacherDao teacherDao = new TeacherDao();
//        teacherDao.insert(teacher); // ※次に insert() メソッドも作成します
//
//        // 教員一覧にリダイレクト（またはフォワード）
//        res.sendRedirect("TeacherList.action");
//    }
//}


package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class TeacherCreateAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザーを取得（所属校の取得などに使用可能）

        HttpSession session = req.getSession();

        Teacher loginUser = (Teacher) session.getAttribute("user");

        // 空の Teacher オブジェクトを作成（フォームの初期値として利用）

        Teacher teacher = new Teacher();

        // JSPで使用するためリクエストスコープにセット

        req.setAttribute("teacher", teacher);

        // 登録フォーム画面にフォワード

        req.getRequestDispatcher("teacher_create.jsp").forward(req, res);




    }

}

