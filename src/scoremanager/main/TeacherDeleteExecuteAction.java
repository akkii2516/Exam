package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteExecuteAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // セッションからログインユーザーを取得（必要に応じて）

        HttpSession session = req.getSession();

        Teacher loginUser = (Teacher) session.getAttribute("user");

        // 削除する対象の教師IDをリクエストパラメータから取得

        String id = req.getParameter("id");

        // TeacherDaoを使ってデータベースから削除処理を実行

        TeacherDao teacherDao = new TeacherDao();

        teacherDao.delete(id); // `delete()` メソッドを定義していることを前提とします。

        req.getRequestDispatcher("teacher_delete_done.jsp").forward(req, res);

    }

}

