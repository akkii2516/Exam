package scoremanager.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.StudentDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // フォームからのデータ取得
        String[] studentNos = req.getParameterValues("studentNoList");
        String[] pointStrs = req.getParameterValues("pointList");
        String subject_cd = req.getParameter("subject");
        String class_num = req.getParameter("classNum");
        System.out.println(class_num);
        int count = Integer.parseInt(req.getParameter("count"));

        // エラーメッセージ格納用
        Map<String, String> errors = new HashMap<>();
        List<Test> tests = new ArrayList<>();

        // 入力データを検証・加工
        for (int i = 0; i < studentNos.length; i++) {
            String no = studentNos[i];
            String pointStr = pointStrs[i];

            int point = 0;
            try {
                point = Integer.parseInt(pointStr);
                if (point < 0 || point > 100) {
                    errors.put(no, "0～100の範囲で入力してください");
                }
            } catch (NumberFormatException e) {
                errors.put(no, "数字を入力してください");
            }


            // 成績データ作成
            Test test = new Test();
            Student student = new Student();
            StudentDao studentDao = new StudentDao();
            SubjectDao subjectDao = new SubjectDao();
            student.setNo(no);
            student.setEntYear(studentDao.get(no).getEntYear());
            student.setName(studentDao.get(no).getName());
            test.setStudent(student);

            Subject subject = new Subject();
            subject.setCd(subject_cd);
            subject.setName(subjectDao.get(subject_cd, teacher.getSchool()).getName());

            test.setSubject(subject);

            test.setSchool(teacher.getSchool());
            test.setNo(count);
            test.setPoint(point);
            test.setClassNum(class_num);
            tests.add(test);

            session.setAttribute("selectedF4", count);
            session.setAttribute("selectedF1", studentDao.get(no).getEntYear());
            session.setAttribute("selectedF2",class_num);
            session.setAttribute("selectedF3", subject_cd);

        }
        // エラーがある場合は、入力ページに戻す
        if (!errors.isEmpty()) {
            session.setAttribute("errors", errors);
            session.setAttribute("tests", tests); // エラーがあっても再表示のため保持
            res.sendRedirect("TestRegist.action");
            return;
        }

        // 保存処理
        TestDao testDao = new TestDao();
        boolean isSaved = testDao.save(tests);
        session.removeAttribute("errors");
        session.removeAttribute("tests");
        if (isSaved) {
            res.sendRedirect("test_regist_done.jsp");
        } else {
            res.sendRedirect("TestRegist.action");
        }
    }
}
