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
import dao.TestDao;
import tool.Action;

public class TestRegistExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");  // セッションから教師情報を取得


        // フォームからの入力データを取得
        String subject_cd = req.getParameter("subject");
        int count = Integer.parseInt(req.getParameter("count"));
        String class_num = req.getParameter("classNum");
        String[] student_no = req.getParameterValues("regist");
		for (String item : student_no) {
			int point = Integer.parseInt(req.getParameter("point")); // 得点（test_regist.jsp上では点数）

        Map<String, String> errors = new HashMap<>();
        //TestDaoの初期化
        TestDao testDao = new TestDao();

        List<Test> tests = new ArrayList<>();
        // 点数が0よりも小さいまたは、100よりも大きい場合
        if (0 < point || 100 < point) {
            errors.put("1", "0～100の範囲で入力してください");//jsp側では${errors.get("1") }のように()の中に1を指定してあげる
            session.setAttribute("errors", errors);  // セッションにエラーを保存
            res.sendRedirect("TestRegist.action");  // 登録フォームにリダイレクト
        } else {
             // 成績情報を作成
        		Test test = new Test();
        		Student student = new Student();
        		student.setNo(item);
        		test.setStudent(student);//学生番号
        		Subject subject = new Subject();
        		subject.setCd(subject_cd);
                test.setSubject(subject);//科目コード
                test.setSchool(teacher.getSchool());  // 学校コード
                test.setNo(count);//回数
                test.setPoint(point);//得点
                test.setClassNum(class_num);//科目コード
                tests.add(test);
        }
    	// 成績の情報をデータベースに保存
        boolean isSaved = testDao.save(tests);
        // 保存が成功した場合の処理
        if (isSaved) {
        	session.removeAttribute("errors");
            session.setAttribute("successMessage", "登録が完了しました");
            res.sendRedirect("test_regist_done.jsp");  // 成績登録完了ページにリダイレクト
         }
      }
   }
}