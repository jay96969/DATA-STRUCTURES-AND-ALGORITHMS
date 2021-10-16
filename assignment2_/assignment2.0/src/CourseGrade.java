

public class CourseGrade implements CourseGrade_ {

	public String coursetitle ;
	
	public String coursetitle() {
		return coursetitle;
	}

	public String coursenum ;
	public String coursenum() {	
		return coursenum;
	}
	public String grade1;
	public String grade1() {
		return grade1;
	}

	
  public int gradepoint()
	{
	  if (grade1.equals("A")) return 10;
	

	else if (grade1.equals("Aminus")) return 9;

	else if (grade1.equals("B" )) return 8;

	else if (grade1.equals("Bminus")) return 7;

	else if (grade1.equals("C")) return 6;

	else if (grade1.equals("Cminus") ) return 5;

	else if (grade1.equals("D") ) return 4;

	else return 0;
}


@Override
public GradeInfo_ grade() {
	// TODO Auto-generated method stub
	return null;
}}


