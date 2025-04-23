package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー（教員）情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAOを使って該当学校の科目リストを取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> list = subjectDao.filter(teacher.getSchool());

        // 科目リストをリクエストスコープにセット
        req.setAttribute("subject_list", list);

        // subject_list.jsp にフォワード
        req.getRequestDispatcher("subject_list.jsp").forward(req, res);
    }
}
