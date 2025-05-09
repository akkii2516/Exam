
	package scoremanager.main;
	import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.ClassNum;
import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;
	public class ClassCreateAction extends Action {
	    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	        // セッションからユーザー情報を取得
	        HttpSession session = req.getSession();
	        Teacher teacher = (Teacher) session.getAttribute("user");
	        // 科目一覧を取得
	        ClassNumDao classNumDao = new ClassNumDao();
	        List<String> list = classNumDao.filter(teacher.getSchool());
	        // 空のSubjectをリクエストにセット（フォーム用）
	        ClassNum classnum = new ClassNum();
	        req.setAttribute("ClassNum", classnum);
	        req.setAttribute("class_list", list);
	        // JSPへフォワード
	        req.getRequestDispatcher("class_create.jsp").forward(req, res);
	    }
	}
