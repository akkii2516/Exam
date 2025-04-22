//仮段階 プッシュまだ
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Subject;
import dao.SubjectDao;
import tool.Action;

public class SubjectUpdateExecuteAction extends Action {
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

        subjectDao.save(subject);

        req.getRequestDispatcher("subject_update_done.jsp").forward(req, res);

    }
}