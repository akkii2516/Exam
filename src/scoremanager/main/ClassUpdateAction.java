package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class ClassUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		String classNum = req.getParameter("classNum");

		ClassNumDao ClassNumDao = new ClassNumDao();
		//System.out.println("cd: " + cd);
		//System.out.println("school: " + teacher.getSchool());
		ClassNum classnum = ClassNumDao.get(classNum,teacher.getSchool());
		//System.out.println(SubjectDao.get(cd,teacher.getSchool()));

		//リクエストにデータをセット
		req.setAttribute("classNum", classnum);
		//System.out.println(subject);

		//指定されたJSPページ("class_update.jsp")へフォワード
		req.getRequestDispatcher("class_update.jsp").forward(req, res);
	}
}