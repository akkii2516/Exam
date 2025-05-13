package scoremanager.main;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.School;
import bean.Teacher;
import dao.SchoolDao;
import dao.TeacherDao;
import tool.Action;

//package scoremanager.main;
//
//
//import java.util.List;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//
//import bean.Teacher;
//import dao.TeacherDao;
//import tool.Action;
//
//	// 明日、明後日らへんにやるかもしれない でも途中までは頑張る！
//	public class TeacherListAction extends Action {
//	    @Override
//	    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//	        HttpSession session = req.getSession();
//	        Teacher teacher = (Teacher) session.getAttribute("user");
//
//	        TeacherDao teacherDao = new TeacherDao();
//	        List<String> classNum = teacherDao.filter(teacher.getSchool());
//
//	        req.setAttribute("classNum", classNum);
//	        req.getRequestDispatcher("class_list.jsp").forward(req, res);
//	    }
//	}
//

//public class TeacherListAction extends Action {
//
//	@Override
//
//	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
//
//		HttpSession session = req.getSession();
//
//		Teacher loginUser = (Teacher) session.getAttribute("user");
//
//		TeacherDao teacherDao = new TeacherDao();
//
//		List<Teacher> teachers = teacherDao.findBySchool(loginUser.getSchool().getCd());
//
//		req.setAttribute("teachers", teachers);
//
//		req.getRequestDispatcher("teacher_list.jsp").forward(req, res);
//
//	}
//
//}

public class TeacherListAction extends Action {

    @Override

    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

        HttpSession session = req.getSession();

        Teacher loginUser = (Teacher) session.getAttribute("user");

        // 学校コードを取得

        String schoolCd = loginUser.getSchool().getCd();

        // SchoolDaoを使って学校情報を取得

        SchoolDao schoolDao = new SchoolDao();

        School school = schoolDao.get(schoolCd);  // 学校情報を取得

        // TeacherDaoを使って学校に所属する教員一覧を取得

        TeacherDao teacherDao = new TeacherDao();

        List<Teacher> teachers = teacherDao.findBySchool(school);  // School型を渡す

        // JSPに渡す

        req.setAttribute("teachers", teachers);

        req.getRequestDispatcher("teacher_list.jsp").forward(req, res);

    }

}




