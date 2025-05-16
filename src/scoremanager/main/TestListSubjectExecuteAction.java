//2025-04-25
package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import bean.TestListSubject;
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
        SubjectDao subjectDao = new SubjectDao();
        TestListSubjectDao testlistsubjectDao = new TestListSubjectDao();
        //エラー格納用のボックス作成
        Map<String, String> errors = new HashMap<>();

        if (!entYearStr.equals(0) || !classNum.equals(0) || !subjectcd.equals(0)) {
        	//入学年度、クラス、科目すべてが0出ない場合（入力されている場合）
        	int entyearstr = Integer.parseInt(entYearStr);//entYearStrをint型に変更
        	Subject subject = subjectDao.get(subjectcd, teacher.getSchool());
        	//引数の条件で検索
        	List<TestListSubject> test_list = testlistsubjectDao.filter(entyearstr, classNum, subject, teacher.getSchool());

        	// 検索結果をリクエストに設定
        	req.setAttribute("test_list", test_list);
        	if (test_list.equals(null)) {
        		errors.put("error2", "学生情報が存在しませんでした");
        	}
        } else {
        	errors.put("error1", "入学年度とクラスと科目を選択してください");
        }

        // セレクトボックスの表示用データセット
        req.setAttribute("f1", entYearStr);
        req.setAttribute("f2", classNum);
     // 科目一覧を取得してリクエストにセット（※追加）
        List<Subject> subjectList = subjectDao.filter(teacher.getSchool());
        req.setAttribute("f3", subjectList);

        // 結果表示用JSPへフォワード
        req.getRequestDispatcher("test_list_subject.jsp").forward(req, res);
    }
}
