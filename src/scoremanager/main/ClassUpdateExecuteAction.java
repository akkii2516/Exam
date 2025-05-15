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

        String oldClassNumStr = req.getParameter("oldClassNum");

        String newClassNumStr = req.getParameter("newClassNum");

        Map<String, String> errors = new HashMap<>();

        ClassNumDao classNumDao = new ClassNumDao();

        // 新しいクラス名が他に存在していないか確認（自分以外で）

        ClassNum duplicate = classNumDao.get(newClassNumStr, teacher.getSchool());

        if (duplicate != null && !newClassNumStr.equals(oldClassNumStr)) {

            errors.put("f1", "既に使用されているクラス名です");

            session.setAttribute("errors", errors);

            // フォーム再表示用データ

            ClassNum classNum = new ClassNum();

            classNum.setClass_num(oldClassNumStr);

            classNum.setSchool(teacher.getSchool());

            req.setAttribute("classNum", classNum);

            req.getRequestDispatcher("class_update.jsp").forward(req, res);

            return;

        }

        // 更新処理

        ClassNum oldClassNum = new ClassNum();

        oldClassNum.setClass_num(oldClassNumStr);

        oldClassNum.setSchool(teacher.getSchool());

        boolean success = classNumDao.update(oldClassNum, newClassNumStr);

        if (!success) {

            errors.put("f2", "更新に失敗しました");

            session.setAttribute("errors", errors);

            req.setAttribute("classNum", oldClassNum);

            req.getRequestDispatcher("class_update.jsp").forward(req, res);

            return;

        }

        req.getRequestDispatcher("class_update_done.jsp").forward(req, res);

    }

}

