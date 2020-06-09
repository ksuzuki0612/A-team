import java.util.*;
    
public class Staff {
	private int emp_id;				//従業員ID
	private String emp_name;		//名前
	private char emp_gender;		//性別
	private Date birth;		//誕生日
	private String position;	//役職
	private String assignment;		//所属
	private int yearsWorked;	//勤務年数
	private String certificate1 = ""; //資格1
	private String certificate2 = ""; //資格2
	private String certificate3 = ""; //資格3
	private String awardsPunishments1 = "";	//賞罰1
	private String awardsPunishments2 = "";	//賞罰2
	private String awardsPunishments3 = "";	//賞罰3
	private String programmingLanguage;	//使用言語
	
    /**
    * Staffコンストラクタの生成
    */
    public Staff(int emp_id,String emp_name,char emp_gender,Date birth,String position,String assignment,
    int yearsWorked,String certificate1,String certificate2,String certificate3,String awardsPunishments1,
    String awardsPunishments2,String awardsPunishments3,String programmingLanguage){
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_gender = emp_gender;
        this.birth = birth;
        this.position = position;
        this.assignment = assignment;
        this.yearsWorked = yearsWorked;
        this.certificate1 = certificate1;
        this.awardsPunishments1 = awardsPunishments1;
        this.certificate2 = certificate2;
        this.awardsPunishments2 = awardsPunishments2;
        this.certificate3 = certificate3;
        this.awardsPunishments3 = awardsPunishments3;
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
	
	public String getCertificate1() {
		return certificate1;
	}
	
	public String getAwardsPunishments1() {
		return awardsPunishments1;
	}
	
	public String getCertificate2() {
		return certificate2;
	}
	
	public String getAwardsPunishments2() {
		return awardsPunishments2;
	}
	
	public String getCertificate3() {
		return certificate3;
	}
	
	public String getAwardsPunishments3() {
		return awardsPunishments3;
	}
	
	public String getProgrammingLanguage() {
		return programmingLanguage;
	}
}
