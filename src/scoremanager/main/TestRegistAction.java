package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 所属学校に対応するクラス一覧を取得
        ClassNumDao classnumDao = new ClassNumDao();
        List<String> classList = classnumDao.filter(teacher.getSchool());

        // 所属学校に対応する科目一覧を取得
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());

        // リクエストスコープにセット
        req.setAttribute("classlist", classList);
        req.setAttribute("subjectlist", subjectList);

        // フォワード先（JSP）へ遷移
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
