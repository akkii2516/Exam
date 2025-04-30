package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Subject;
import bean.Teacher;
import dao.ClassNumDao;
import dao.SubjectDao;
import tool.Action;

public class TestRegistAction extends Action {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
    	HttpSession session = req.getSession();//セッション
        Teacher teacher = (Teacher)session.getAttribute("user");

			ClassNumDao cNumDao = new ClassNumDao();//クラス番号Daoを初期化
			SubjectDao subjectDao = new SubjectDao();//科目Daoの初期化

			//DBからデータ取得3
                List<String>cNumlist = cNumDao.filter(teacher.getSchool()); //クラス情報
                List<Subject>list = subjectDao.filter(teacher.getSchool()); //科目情報

                LocalDate todaysDate = LocalDate.now();//LocalDateインスタンスを取得

        		int year = todaysDate.getYear();//現在の年を取得

			//リストを初期化
			        List<Integer> entYearSet = new ArrayList<>();
			        //10年前から1年後まで年をリストに追加
			        for (int i = year - 10; i < year + 1; i++) {
			            entYearSet.add(i);
			        }

			//リクエストにクラス番号をセット
			        req.setAttribute("f1", entYearSet);
			        req.setAttribute("f2", cNumlist);
			        req.setAttribute("f3", list);

			        //JSPへフォワード 7
			        req.getRequestDispatcher("test_regist.jsp").forward(req, res);
    }
}
