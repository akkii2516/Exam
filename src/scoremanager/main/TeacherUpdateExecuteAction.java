package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherUpdateExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        // フォームから値を取得
        String originalId = req.getParameter("original_id"); // 元のID（変更前）
        String newId = req.getParameter("id");
        String password = req.getParameter("password");
        String name = req.getParameter("name");

        // Teacherオブジェクトを作成して値をセット
        Teacher teacher = new Teacher();
        teacher.setId(newId);
        teacher.setPassword(password);
        teacher.setName(name);

        // DAOの更新メソッドを呼び出し
        TeacherDao dao = new TeacherDao();
        dao.update(originalId, teacher); // 元のIDを使って更新

        // 完了メッセージまたはリダイレクト
        req.setAttribute("message", "教員情報を更新しました。");
        req.getRequestDispatcher("teacher_update_done.jsp").forward(req, res);
    }
}