package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tool.Action;

public class StudentCreateDoneAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//指定されたJSPページ("student_create_done.jsp")へフォワード
		req.getRequestDispatcher("student_create_done.jsp").forward(req, res);
	}
}