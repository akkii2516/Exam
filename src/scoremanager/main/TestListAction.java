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

public class TestListAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // DAO初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();

        // 学校ごとのクラス・科目・年度・回数リストを準備
        List<String> cNumlist = cNumDao.filter(school);
        List<Subject> subjectList = subjectDao.filter(school);


        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        List<Integer> entYearList = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) {
            entYearList.add(i);
        }

        List<Integer> countList = new ArrayList<>();
        for (int i = 1; i <= 2; i++) {
            countList.add(i);
        }
        // 結果: [1, 2]


        // パラメータ取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // 回数

        // セレクトボックスの表示用データセット
        req.setAttribute("f1", entYearList);
        req.setAttribute("f2", cNumlist);
        req.setAttribute("f3", subjectList);
        req.setAttribute("f4", countList);
        req.setAttribute("selectedF1", f1);
        req.setAttribute("selectedF2", f2);
        req.setAttribute("selectedF3", f3);
        req.setAttribute("selectedF4", f4);


        // 検索条件がすべて正しく入力されていれば検索を実行
        if (isValid(f1) && isValid(f2) && isValid(f3) && isValid(f4)) {
            int entYear = Integer.parseInt(f1);
            String classNum = f2;
            String subjectCd = f3;
            int testNo = Integer.parseInt(f4);

            Subject subject = subjectDao.get(subjectCd, school);
            TestDao testDao = new TestDao();
            List<Test> tests = testDao.filter(entYear, classNum, subject, testNo, school);

            req.setAttribute("tests", tests);
        } else if (anyNotNull(f1, f2, f3, f4)) {
            // 一部のみ選択されている場合はエラー表示
            req.setAttribute("error", "すべての検索条件を選択してください。");
        }

        // JSPへフォワード
        req.getRequestDispatcher("test_list.jsp").forward(req, res);
    }

    private boolean isValid(String value) {
        return value != null && !value.isEmpty() && !value.equals("0");
    }


    // 1つでも何かしら値が入っているか判定
    private boolean anyNotNull(String... values) {
        for (String v : values) {
            if (v != null && !v.equals("0")) return true;
        }
        return false;
    }
}
