package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassDeleteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        // セッションからユーザー情報（クラス or ログインユーザー）を取得し、学校コードを取り出す
        Teacher teacher = (Teacher) session.getAttribute("user");
        School school = teacher.getSchool();

        // リクエストからクラス番号を取得
        String classnum = req.getParameter("class_num");

        // DAO 初期化
        ClassNumDao classnumDao = new ClassNumDao();

        // クラス情報を取得
        ClassNum targetClass = classnumDao.get(classnum, school);

        // リクエストスコープにセット
        req.setAttribute("classnum", targetClass);

        // 確認ページにフォワード
        req.getRequestDispatcher("class_delete.jsp").forward(req, res);
    }
}
