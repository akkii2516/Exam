package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String cd = req.getParameter("cd");
        String name = req.getParameter("name");

        // エラーメッセージ格納用
        Map<String, String> errors = new HashMap<>();

        SubjectDao subjectDao = new SubjectDao();
        // ① 科目の存在チェック
        Subject existingSubject = subjectDao.get(cd, teacher.getSchool());

        if (existingSubject == null) {
            // ② 存在しない場合はエラーメッセージを設定してJSPに戻す
            errors.put("f1", "科目が存在していません");
            session.setAttribute("errors", errors);

            // フォーム再表示用に subject を設定
            Subject subject = new Subject();
            subject.setCd(cd);
            subject.setName(name); // name も一応残しておく（任意）
            req.setAttribute("subject", subject);

            req.getRequestDispatcher("subject_update.jsp").forward(req, res);
            return;
        }

        // ③ 科目が存在すれば更新処理
        Subject subject = new Subject();
        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());

        subjectDao.save(subject);

        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);
    }
}
