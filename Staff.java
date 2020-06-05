import java.util.*;
import java.text.SimpleDateFormat;
import java.io.IOException;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
    
public class Staff {
	static Logger logger = Logger.getLogger(Staff.class.getName());
	private int emp_id;				//è]ã∆àıid
	private String emp_name;		//ñºëO
	private char emp_gender;		//ê´ï 
	private Date birth;		//íaê∂ì˙
	private String position;	//ñêE
	private String assignment;		//èäëÆ
	private int yearsWorked;	//ãŒñ±îNêî
	private List<String> certificate; //éëäi
	private List<String> rewardsPunishments;	//è‹î±
	private String programmingLanguage;	//égópåæåÍ
	
	public int getEmp_id() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return emp_id;
	}
	
	public void setEmp_id(int emp_id) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.emp_id = emp_id;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public String getEmp_name() {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return emp_name;
	}
	
	public void setEmp_name(String emp_name) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.emp_name = emp_name;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public char getEmp_gender() {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return emp_gender;
	}
	
	public void setEmp_gender(char emp_gender) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.emp_gender = emp_gender;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public Date getBirth() {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return birth;
	}
	
	public void setBirth(Date birth) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.birth = birth;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public String getPosition() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return position;
	}
	
	public void setPosition(String position) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.position = position;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public String getAssignment() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return assignment;
	}
	
	public void setAssignment(String assignment) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.assignment = assignment;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public int getYearsWorked() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return yearsWorked;
	}
	
	public void setYearsWorked(int yearsWorked) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.yearsWorked = yearsWorked;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public List<String> getCertificate() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return certificate;
	}
	
	public void setCertificate(List<String> certificate) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.certificate = certificate;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public List<String> getRewardsPunishments() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return rewardsPunishments;
	}
	
	public void setRewardsPunishments(List<String> rewardsPunishments) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.rewardsPunishments = rewardsPunishments;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
	
	public String getProgrammingLanguage() {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
		return programmingLanguage;
	}
	
	public void setProgrammingLanguage(String programmingLanguage) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
		this.programmingLanguage = programmingLanguage;
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
	}
}