package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherCreateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // 文字エンコーディングを設定（日本語対応）
        req.setCharacterEncoding("UTF-8");

        // セッションからログインユーザーを取得し、所属校を特定
        HttpSession session = req.getSession();
        Teacher loginUser = (Teacher) session.getAttribute("user");
        School school = loginUser.getSchool();

        // フォームから送信されたデータを取得
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String password = req.getParameter("password");

        // 教員インスタンスを作成し、値を設定
        Teacher newTeacher = new Teacher();
        newTeacher.setId(id);
        newTeacher.setName(name);
        newTeacher.setPassword(password);
        newTeacher.setSchool(school);

        // 教員情報をデータベースに登録
        TeacherDao teacherDao = new TeacherDao();
        teacherDao.insert(newTeacher);

        // 登録完了後、教員一覧画面にリダイレクト
        res.sendRedirect("TeacherList.action");
    }
}