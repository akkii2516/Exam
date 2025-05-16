//学生別成績一覧

package bean;

import java.io.Serializable;

public class TestListStudent implements Serializable {

	/**
	 * 科目名：String
	 */
	private String subjectName;

	/**
	 * 科目コード：String
	 */
	private String subjectCd;

	/**
	 * 合計：int
	 */
	private int num;

	/**
	 * 点数：int
	 */
	private int point;


	/**
	 * ゲッタ・セッタ
	 */

	public String getSubjectName() {
		return subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}

	public void setSubjectCd(String subjectCd) {
		this.subjectCd = subjectCd;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}



}
