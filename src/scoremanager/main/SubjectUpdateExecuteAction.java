//仮段階 プッシュまだ
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

    	HttpSession session = req.getSession();
    	Teacher teacher = (Teacher)session.getAttribute("user");

    	String cd = "";
    	String name = "";
    	//科目Daoを初期化
        Subject subject = new Subject();
        SubjectDao subjectDao = new SubjectDao();

      //リクエストにデータをセット
        cd = req.getParameter("cd");
        name = req.getParameter("name");

        subject.setCd(cd);
        subject.setName(name);
        subject.setSchool(teacher.getSchool());  // ← これが大事！

        subjectDao.save(subject);

        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);

    }
}