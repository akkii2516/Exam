package scoremanager.main;

import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // 各種リストを取得（入学年度・クラス・科目・回数など）
        Set<String> entYearSet = new StudentDao().save(Student.getstudent());
        List<String> classList = new ClassNumDao().filter(teacher.getSchool());
        List<Subject> subjectList = new SubjectDao().filter(teacher.getSchool());
        List<Integer> timesList = new TestDao().filter(teacher.getSchool());

        // JSPへ渡すデータをセット
        req.setAttribute("ent_year_set", entYearSet);
        req.setAttribute("class_list", classList);
        req.setAttribute("subject_list", subjectList);
        req.setAttribute("times_list", timesList);

        // フォワード先（テスト登録画面）
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
