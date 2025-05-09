//仮段階
package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.SubjectDao;
import tool.Action;

public class SubjectDeleteExecuteAction extends Action {
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();
    	Teacher teacher = (Teacher) session.getAttribute("user");
    	System.out.println(teacher);

    	String cd = "";
    	String name = "";
    	//科目Daoを初期化
        SubjectDao subjectDao = new SubjectDao();

      //リクエストにデータをセット
        cd = req.getParameter("subject_cd");
        name = req.getParameter("subject_name");

        System.out.println(cd);
        System.out.println(name);
        Subject subject = subjectDao.get(cd, teacher.getSchool());
        System.out.println(subjectDao.get(cd, teacher.getSchool()));
        subject.setSchool(teacher.getSchool());
        subjectDao.delete(subject);

        req.getRequestDispatcher("subject_delete_done.jsp").forward(req, res);

    }
}