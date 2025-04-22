package scoremanager.main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectCreateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // セッションからログインユーザー情報（教員）を取得
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        // フォームから送信されたパラメータを取得
        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // 入力値をセットしたSubjectオブジェクトを作成
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        // エラーメッセージ格納用
        Map<String, String> errors = new HashMap<>();

        // バリデーションチェック
        if (cd == null || cd.length() != 3) {
            errors.put("cd", "科目コードは3文字で入力してください");
        }

        if (name == null || name.trim().isEmpty()) {
            errors.put("name", "科目名を入力してください");
        }

        SubjectDao dao = new SubjectDao();

        // 科目コードの重複チェック（同じ学校内で）
        if (dao.get(cd, teacher.getSchool()) != null) {
            errors.put("cd", "この科目コードはすでに登録されています");
        }

        // エラーがある場合は、入力ページに戻す
        if (!errors.isEmpty()) {
            List<Subject> list = dao.filter(teacher.getSchool());

            session.setAttribute("errors", errors);
            session.setAttribute("subject", subject);
            session.setAttribute("subject_list", list);
            session.setAttribute("errorMessage", "入力内容に誤りがあります");
            res.sendRedirect("subject_create.jsp");
            return;
        }

        // 登録処理
        boolean result = dao.save(subject);

        if (result) {
            session.removeAttribute("errors");
            session.setAttribute("successMessage", "科目を登録しました。");
            res.sendRedirect("subject_create_done.jsp");
        } else {
            session.setAttribute("errorMessage", "登録に失敗しました。");
            res.sendRedirect("subject_create.jsp");
        }
    }
}
