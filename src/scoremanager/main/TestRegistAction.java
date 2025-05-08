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
        // セッションから教員情報と所属学校を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // セレクトボックス用データの準備（クラス、科目、入学年度、回数）
        List<String> classNumList = new ClassNumDao().filter(school);
        List<Subject> subjectList = new SubjectDao().filter(school);
        List<Integer> entYearList = getEntYearList();
        List<Integer> testCountList = getTestCountList();

        // リクエストから検索条件を取得
        String f1 = req.getParameter("f1"); // 入学年度
        String f2 = req.getParameter("f2"); // クラス
        String f3 = req.getParameter("f3"); // 科目コード
        String f4 = req.getParameter("f4"); // テスト回数

        // フォーム表示用データをリクエストにセット
        req.setAttribute("f1", entYearList);
        req.setAttribute("f2", classNumList);
        req.setAttribute("f3", subjectList);
        req.setAttribute("f4", testCountList);
        req.setAttribute("selectedF1", f1);
        req.setAttribute("selectedF2", f2);
        req.setAttribute("selectedF3", f3);
        req.setAttribute("selectedF4", f4);

        // 全ての条件が選択されている場合のみ検索処理実行
        if (isValid(f1) && isValid(f2) && isValid(f3) && isValid(f4)) {
            int entYear = Integer.parseInt(f1);
            String classNum = f2;
            String subjectCd = f3;
            int testNo = Integer.parseInt(f4);

            Subject subject = new SubjectDao().get(subjectCd, school);
            List<Test> tests = new TestDao().filter(entYear, classNum, subject, testNo, school);

            req.setAttribute("tests", tests);
        } else if (anySelected(f1, f2, f3, f4)) {
            req.setAttribute("error", "すべての検索条件を選択してください。");
        }

        // JSPへフォワード
        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }

    // 現在の年を基準に過去10年分の入学年度をリストで返す
    private List<Integer> getEntYearList() {
        List<Integer> list = new ArrayList<>();
        int thisYear = LocalDate.now().getYear();
        for (int i = thisYear - 10; i <= thisYear; i++) {
            list.add(i);
        }
        return list;
    }

    // テスト回数（1～10）をリストで返す
    private List<Integer> getTestCountList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
        return list;
    }

    // 有効な値かどうか（nullでなく、"0"でない）
    private boolean isValid(String value) {
        return value != null && !value.equals("0");
    }

    // どれか1つでも選択されていれば true
    private boolean anySelected(String... values) {
        for (String v : values) {
            if (v != null && !v.equals("0")) return true;
        }
        return false;
    }
}
