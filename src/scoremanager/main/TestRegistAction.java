package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Subject;
import bean.Teacher;
import bean.Test;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // DAOの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        // クラス番号と科目を学校ごとに取得
        List<String> cNumlist = cNumDao.filter(teacher.getSchool());
        List<Subject> list = subjectDao.filter(teacher.getSchool());

        // 現在の年を取得し、入学年度リストを作成（10年前〜今年）
        LocalDate todaysDate = LocalDate.now();
        int year = todaysDate.getYear();
        List<Integer> entYearSet = new ArrayList<>();
        for (int i = year - 10; i <= year; i++) {
            entYearSet.add(i);
        }

        // 【追加】回数リスト（例：1〜10回）
        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            countList.add(i);
        }

        // リクエストパラメータから検索条件を取得
        String f1 = req.getParameter("f1");  // 入学年度
        String f2 = req.getParameter("f2");  // クラス
        String f3 = req.getParameter("f3");  // 科目
        String f4 = req.getParameter("f4");  // 回数        // リクエストスコープにセット（フィルタリングなしで、すべてのデータを渡す）
        req.setAttribute("f1", entYearSet);          // 入学年度
        req.setAttribute("f2", cNumlist);            // クラス
        req.setAttribute("f3", list);                // 科目
        req.setAttribute("f4", countList);           // 回数
        req.setAttribute("selectedF1", f1);          // 選択された入学年度
        req.setAttribute("selectedF2", f2);          // 選択されたクラス
        req.setAttribute("selectedF3", f3);          // 選択された科目
        req.setAttribute("selectedF4", f4);          // 選択された回数

        // JSPにフォワード
     // 検索条件が全て揃っていれば検索実行
        if (f1 != null && f2 != null && f3 != null && f4 != null &&
            !f1.equals("0") && !f2.equals("0") && !f3.equals("0") && !f4.equals("0")) {

            int entYear = Integer.parseInt(f1);
            String classNum = f2;
            String subjectCd = f3;
            int testNo = Integer.parseInt(f4);

            School school = teacher.getSchool();
            Subject subject = subjectDao.get(subjectCd, school);

            TestDao testDao = new TestDao();
            List<Test> tests = testDao.filter(entYear, classNum, subject, testNo, school);

            req.setAttribute("tests", tests); // ← JSP でループされる
        }

        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
