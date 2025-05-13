package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession(); // セッション

        Teacher teacher = (Teacher)session.getAttribute("user");

        // teacher_list.jspから変更を押したユーザーコードを取得

        String id = req.getParameter("id");

        // TeacherDaoを初期化

        TeacherDao teacherDao = new TeacherDao();

        // インスタンスメソッドget()を呼び出す

        Teacher teacherget = teacherDao.get(id); // ここを修正

        // リクエストにデータをセット

        req.setAttribute("teacher", teacherget);  // 'subject' → 'teacher' に修正

        // 指定されたJSPページ("teacher_delete.jsp")へフォワード

        req.getRequestDispatcher("teacher_delete.jsp").forward(req, res);

    }

}

