import java.util.*;
    
public class Staff {
	private int emp_id;				//従業員ID
	private String emp_name;		//名前
	private char emp_gender;		//性別
	private Date birth;		//誕生日
	private String position;	//役職
	private String assignment;		//所属
	private int yearsWorked;	//勤務年数
	private List<String> certificate; //資格
	private List<String> awardsPunishments;	//賞罰
	private String programmingLanguage;	//使用言語
	
    /**
    * Staffコンストラクタの生成
    */
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
	
    /**
    *getterの生成
    */
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
