//仮段階 プッシュまだ
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//subject_list.jspから変更を押した科目コードを取得
		String cd = req.getParameter("cd");

		//科目Daoを初期化
		SubjectDao SubjectDao = new SubjectDao();
		System.out.println("cd: " + cd);
		System.out.println("school: " + teacher.getSchool());
		//科目コード、学校コードをもとに科目の詳細データを取得
		Subject subject = SubjectDao.get(cd,teacher.getSchool());
		System.out.println(SubjectDao.get(cd,teacher.getSchool()));

		//リクエストにデータをセット
		req.setAttribute("subject", subject);
		System.out.println(subject);

		//指定されたJSPページ("subject_update.jsp")へフォワード
		req.getRequestDispatcher("subject_update.jsp").forward(req, res);
	}
}