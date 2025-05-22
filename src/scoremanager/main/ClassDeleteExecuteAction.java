package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassDeleteExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    HttpSession session = req.getSession();
	    Teacher teacher = (Teacher) session.getAttribute("user");
	    School school = teacher.getSchool();

	    String classNumId = req.getParameter("class_num");
	    ClassNumDao classNumDao = new ClassNumDao();

	    boolean deleted = classNumDao.delete(classNumId, school);

	    if (deleted) {
	        req.setAttribute("message", "クラス「" + classNumId + "」を削除しました。");
	        req.getRequestDispatcher("class_delete_done.jsp").forward(req, res);
	    } else {
	         req.getRequestDispatcher("class_delete_error.jsp").forward(req, res);
	    }
	}
}

