//2025-04-25
package scoremanager.main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        Teacher teacher = (Teacher) req.getSession().getAttribute("user");

        // リクエストパラメータを取得
        String entYearStr = req.getParameter("f1");
        String classNum = req.getParameter("f2");
        String isAttendStr = req.getParameter("f3");

        // 入学年度
        int entYear = 0;
        if (entYearStr != null && !entYearStr.isEmpty()) {
            try {
                entYear = Integer.parseInt(entYearStr);
            } catch (NumberFormatException e) {
                return;
            }
        }

        // 在学フラグの変換
        boolean isAttend = "1".equals(isAttendStr);

        // 学生情報の検索
        StudentDao sDao = new StudentDao();
        List<Student> students = sDao.filter(teacher.getSchool(), entYear, classNum, isAttend);

        // 学生リストと検索条件をリクエストにセット
        req.setAttribute("students", students);
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
        req.setAttribute("f3", isAttendStr);

        // 結果表示用JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }
}
