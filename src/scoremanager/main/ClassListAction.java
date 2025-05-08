package scoremanager.main;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassListAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        HttpSession session = req.getSession();
        Teacher teacher = (Teacher) session.getAttribute("user");

        ClassNumDao classNumDao = new ClassNumDao();
        List<String> classNums = classNumDao.filter(teacher.getSchool());

        req.setAttribute("classNums", classNums);
        req.getRequestDispatcher("class_list.jsp").forward(req, res);
    }
}