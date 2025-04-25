package scoremanager.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
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

        // 所属学校の学生データから入学年度一覧を取得
        StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.filter(teacher.getSchool(), true); // 在学中の学生のみ
        Set<Integer> entYearSet = new HashSet<>();
        for (Student s : students) {
            entYearSet.add(s.getEntYear());
        }
        List<Integer> entYearList = new ArrayList<>(entYearSet);
        Collections.sort(entYearList, Collections.reverseOrder()); // 必要に応じて降順

        // リクエストスコープにセット
        req.setAttribute("classlist", classList);
        req.setAttribute("subjectlist", subjectList);
        req.setAttribute("entyearlist", entYearList);

        // フォワード先（JSP）へ遷移
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
