package scoremanager.main;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectListAction extends Action{
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		// TODO 自動生成されたメソッド・スタブ

	HttpSession session = req.getSession();//セッション
	Teacher teacher = (Teacher)session.getAttribute("user");

	dao.SubjectDao SubjectDao = new SubjectDao();

	Subject subject = SubjectDao.filter(teacher.getSchool());



	}

}
