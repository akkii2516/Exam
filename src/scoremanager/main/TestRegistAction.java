package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Test;
import tool.Action;

public class TestRegistAction extends Action{

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		String entYear = req.getParameter("f1");
		String className = req.getParameter("f2");
		String subject = req.getParameter("f3");
		String times = req.getParameter("f4");

		// 検索ロジック（データベースから取得など）
		List<Test> resultList = testService.search(entYear, className, subject, times);

		// 結果をJSPへ渡す
		request.setAttribute("tests", resultList);
		request.getRequestDispatcher("testList.jsp").forward(req, res);

}
	}
