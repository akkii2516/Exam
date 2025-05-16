package bean;

public class TestListSubject {

	private String subjectName;
	private String subjectCd;
	private int num;
	private int point;

	private String classNum;        // ← 追加
	private Student student;        // ← 追加（Studentクラスは既に存在すると仮定）

	// --- Getter / Setter ---
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	public String getSubjectCd() {
		return subjectCd;
	}
	public void setSubjectCd(String subjectCd){
		this.subjectCd = subjectCd;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num){
		this.num = num;
	}

	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}

	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
}
