//仮段階
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.TeacherDao;
import tool.Action;

public class TeacherDeleteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		HttpSession session = req.getSession();//セッション
		Teacher teacher = (Teacher)session.getAttribute("user");

		//teacher_list.jspから変更を押したユーザーコードを取得
		String id = req.getParameter("id");

		//科目Daoを初期化
		TeacherDao teacherDao = new TeacherDao();
		//科目コード、学校コードをもとに科目の詳細データを取得
		Teacher teacherget = TeacherDao.get(id);

		//リクエストにデータをセット
		req.setAttribute("subject", subject);

		//指定されたJSPページ("subject_delete.jsp")へフォワード
		req.getRequestDispatcher("teacher_delete.jsp").forward(req, res);
	}
}