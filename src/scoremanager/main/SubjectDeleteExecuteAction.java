//仮段階
package scoremanager.main;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

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

        subjectDao.delete(subject);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

    }
}