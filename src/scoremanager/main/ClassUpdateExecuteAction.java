package scoremanager.main;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        String classnum = req.getParameter("ClassNum");

        System.out.println(classnum);
        // エラーメッセージ格納用
        Map<String, String> errors = new HashMap<>();

        ClassNumDao classNumDao = new ClassNumDao();
        // ① クラスの存在チェック
        ClassNum existingClassNum = classNumDao.get(classnum, teacher.getSchool());

        if (existingClassNum != null) {
            // ② 存在してたら場合はエラーメッセージを設定してJSPに戻す
            errors.put("f1", "重複しています");
            session.setAttribute("errors", errors);

            // フォーム再表示用に subject を設定
            ClassNum classNum = new ClassNum();
            classNum.setClass_num(classnum);
            req.setAttribute("ClassNum", classnum);

            req.getRequestDispatcher("class_update.jsp").forward(req, res);
            return;
        }

        // ③ クラスが存在すれば更新処理
        ClassNum classNum = new ClassNum();
        classNum.setClass_num(classnum);
        classNum.setSchool(teacher.getSchool());

        classNumDao.save(classNum);

        req.getRequestDispatcher("class_update_done.jsp").forward(req, res);
    }
}