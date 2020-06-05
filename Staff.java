import java.util.*;
    
public class Staff {
	private int emp_id;				//�]�ƈ�id
	private String emp_name;		//���O
	private char emp_gender;		//����
	private Date birth;		//�a����
	private String position;	//��E
	private String assignment;		//����
	private int yearsWorked;	//�Ζ��N��
	private List<String> certificate; //���i
	private List<String> awardsPunishments;	//�ܔ�
	private String programmingLanguage;	//�g�p����
	
    public Staff(int emp_id,String emp_name,char emp_gender,Date birth,String position,String assignment,
      int yearsWorked,List<String> certificate,List<String> awardsPunishments,String programmingLanguage){
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_gender = emp_gender;
        this.birth = birth;
        this.position = position;
        this.assignment = assignment;
        this.yearsWorked = yearsWorked;
        this.certificate = certificate;
        this.awardsPunishments = awardsPunishments;
        this.programmingLanguage = programmingLanguage;
    }
    
	public int getEmp_id() {
		return emp_id;
	}
	
	public String getEmp_name() {
		return emp_name;
	}
	
	public char getEmp_gender() {
		return emp_gender;
	}
	
	public Date getBirth() {
		return birth;
	}
	
	public String getPosition() {
		return position;
	}
	
	public String getAssignment() {
		return assignment;
	}
	
	public int getYearsWorked() {
		return yearsWorked;
	}
	
	public List<String> getCertificate() {
		return certificate;
	}
	
	public List<String> getAwardsPunishments() {
		return awardsPunishments;
	}
	
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
}