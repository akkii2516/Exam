package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassCreateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー情報（教員）を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // フォームから送信されたパラメータを取得
        String classno = req.getParameter("class_num");

        // 入力値をセットしたSubjectオブジェクトを作成
        ClassNum classnum = new ClassNum();
        classnum.setClass_num(classno);
        classnum.setSchool(teacher.getSchool());

        // エラーメッセージ格納用
        Map<String, String> errors = new HashMap<>();

        // バリデーションチェック
        if (classno == null || classno.length() != 3) {
            errors.put("er1", "クラス名は3文字で入力してください");
        }

        ClassNumDao dao = new ClassNumDao();

        // 科目コードの重複チェック（同じ学校内で）
        if (dao.get(classno, teacher.getSchool()) != null) {
            errors.put("er2", "クラス名が重複しています");
        }

        // エラーがある場合は、入力ページに戻す
        if (!errors.isEmpty()) {
            List<String> list = dao.filter(teacher.getSchool());

            session.setAttribute("errors", errors);
            session.setAttribute("ClassNum", classnum);
            session.setAttribute("class_list", list);
            session.setAttribute("errorMessage", "入力内容に誤りがあります");
            res.sendRedirect("ClassCreate.action");
            return;
        }

        // 登録処理
        boolean result = dao.save(classnum);

        if (result) {
            session.removeAttribute("errors");
            session.setAttribute("successMessage", "科目を登録しました。");
            res.sendRedirect("class_create_done.jsp");
        } else {
            session.setAttribute("errorMessage", "登録に失敗しました。");
            res.sendRedirect("class_create.jsp");
        }
    }
}