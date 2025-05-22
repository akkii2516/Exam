//2025-04-25
package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
import dao.ClassNumDao;
import dao.SubjectDao;
import dao.TestListSubjectDao;
import tool.Action;

public class TestListSubjectExecuteAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	// セッションから教員情報を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // リクエストパラメータを取得
        String entYearStr = req.getParameter("f1");//入学年度
        String classNum = req.getParameter("f2");//クラス
        String subjectcd = req.getParameter("f3");//科目

        //Daoの初期化
        ClassNumDao cNumDao = new ClassNumDao();
        SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
        TestListSubjectDao testlistsubjectDao = new TestListSubjectDao();
        //エラー格納用のボックス作成
        Map<String, String> errors = new HashMap<>();

        //現在の日時取得
        LocalDate now = LocalDate.now();
        int currentYear = now.getYear();

        //入学年度一覧
        List<Integer> entYearList = new ArrayList<>();
        for (int i = currentYear - 10; i <= currentYear; i++) {
            entYearList.add(i);
        }

        //クラス一覧
        List<String> cNumlist = cNumDao.filter(teacher.getSchool());

     // 全ての入力がされているかチェック
        if (!entYearStr.equals("0") && !classNum.equals("0") && !subjectcd.equals("0")) {
            // 入学年度、クラス、科目すべてが入力されている場合
            int entyearstr = Integer.parseInt(entYearStr); // 入学年度をint型に変換
            Subject subjects = subjectDao.get(subjectcd, teacher.getSchool());
            List<TestListSubject> test_list = testlistsubjectDao.filter(entyearstr, classNum, subjects, teacher.getSchool());

            req.setAttribute("subjects", subjects);
            req.setAttribute("subjectcd", subjectcd);
            req.setAttribute("test_list", test_list);
            if (test_list == null || test_list.isEmpty()) {
                errors.put("error2", "学生情報が存在しませんでした");
            }
        } else {
            // 一つでも未選択の項目がある場合
            errors.put("error1", "入学年度とクラスと科目を選択してください");
            // セレクトボックスの表示用データセット
            req.setAttribute("f1", entYearList);
            req.setAttribute("f2", cNumlist);
            req.setAttribute("f3", subjectList);

            req.setAttribute("entYearStr", entYearStr);
            req.setAttribute("classNum", classNum);
            req.setAttribute("errors", errors);
            req.getRequestDispatcher("test_list.jsp").forward(req, res);
            System.out.println("error1");
            return;
        }


        // セレクトボックスの表示用データセット
        req.setAttribute("f1", entYearList);
        req.setAttribute("f2", cNumlist);
        req.setAttribute("f3", subjectList);

        req.setAttribute("entYearStr", entYearStr);
        req.setAttribute("classNum", classNum);
        req.setAttribute("subjectcd", subjectcd);
        req.setAttribute("errors", errors);



        // 結果表示用JSPへフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}
