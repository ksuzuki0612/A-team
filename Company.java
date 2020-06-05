import java.util.*;
import java.text.*;
import java.io.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.function.Supplier;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Company {
    static Logger logger = Logger.getLogger(Company.class.getName());
    static final String mysqlURL = "jdbc:mysql://localhost:3306/employee?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    //ÉGÉâÅ[ÇÃÉÅÉbÉZÅ[ÉW
    private String msg = "";
       
    public static String allFild(Staff staff){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        String all1 = "emp_id, emp_name, emp_gender, birth, position, assignment, yearsWorked";
        String all2 = "";
        for(int j = 1; j < (staff.getCertificate().size()+1); j++){
            all2 += ",certificate" + j;
        }
        String all3 = "";
        for(int j = 1; j < (staff.getRewardsPunishments().size()+1); j++){
            all3 += ",rewardsPunishments" + j;
        }
        String all = all1 + all2 + all3 +", programmingLanguage";
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return all;
    }
    
    public static String allValue(Staff staff){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        String strBirth = new SimpleDateFormat("yyyy-MM-dd").format(staff.getBirth());
        String insertValue1 = ") VALUES(" + staff.getEmp_id() + ", '" + staff.getEmp_name() + "', '" + staff.getEmp_gender() + 
        "', '" + strBirth + "', '" + staff.getPosition() + "', '" + staff.getAssignment() + "', " + staff.getYearsWorked();
        String insertValue2 = "";
        for(int j = 0; j<staff.getCertificate().size(); j++){
            insertValue2 += ", '"; 
            insertValue2 += staff.getCertificate().get(j);
            insertValue2 += "'"; 
        }
        String insertValue3 = "";
        for(int j = 0; j<staff.getRewardsPunishments().size(); j++){
            insertValue3 += ", '"; 
            insertValue3 += staff.getRewardsPunishments().get(j);
            insertValue3 += "'"; 
        }
        String insertValue = insertValue1 + insertValue2 + insertValue3 + ", '" + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return insertValue;
    }
    
    //è]ã∆àıÇÃìoò^
    public void addStaff(Staff staff, String userID, String password) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            String insertBefore = "INSERT INTO emp(";
            stmt.executeUpdate(insertBefore + allFild(staff) + allValue(staff));
            stmt.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //è]ã∆àıÇÃçÌèú
    public void deleteStaff(int delete, String userID, String password) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try{
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            String sql1 = "DELETE FROM emp WHERE emp_id = " + delete + ";";
            String sql2 = "DELETE FROM projecthistory WHERE emp_id = " + delete + ";";
            String sql3 = "DELETE FROM users WHERE emp_id = " + delete + ";";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            stmt.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //è]ã∆àıÇÃçXêV
    public void updateStaff(int updateID, Staff staff, String userID, String password) {
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        deleteStaff(updateID, userID, password);
        addStaff(staff, userID, password);
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public static void employeeView(String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT emp_id, emp_name, emp_gender, position, assignment FROM emp;");
            System.out.println("iD    ñºëO       ê´ï   ñêE       èäëÆ");
            while(rs.next()){
                int emp_id = rs.getInt("emp_id");
                String emp_name = rs.getString("emp_name");
                String emp_gender = rs.getString("emp_gender");
                String position = rs.getString("position");
                String assignment = rs.getString("assignment");
                System.out.println(String.format("%-5s %-10s %-5s %-10s %-10s",emp_id, emp_name, emp_gender, position, assignment));
            }
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public static void informationView(String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
            System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
            while(rs.next()){
	            System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public static void assignmentView(String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT project.pj_id, project.pj_name, count(*) AS nofEmp FROM projectHistory LEFT JOIN project ON projectHistory.pj_id = project.pj_id GROUP BY pj_id;");
            System.out.println("ProjcetiD    Projectñº         êlêî");
            while(rs.next()){
                int pj_id = rs.getInt("pj_id");
                String pj_name = rs.getString("pj_name");
                int nofEmp = rs.getInt("nofEmp");
                System.out.println(String.format("%-12s %-17s %-5s",pj_id, pj_name, nofEmp));
            }
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByEmp_id(int emp_id, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_id LIKE '%" + emp_id +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByEmp_name(String emp_name, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_name LIKE '%" + emp_name +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByEmp_gender(char emp_gender, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_gender LIKE '%" + emp_gender +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByBirth(String birth, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE birth LIKE '%" + birth +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByPosition(String position, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE position LIKE '%" + position +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByAssignment(String assignment, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE assignment LIKE '%" + assignment +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByYearsWorked(int yearsWorked, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE yearsWorked LIKE '%" + yearsWorked +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByCertificate(String certificate, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE certificate1 = '" + certificate + "' or certificate2 = '" + certificate + "' or certificate3 = '" + certificate + "';");
            while(rs.next()){
                if(rs.getString(8).contains(certificate)){
                    System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                    System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
                }
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByRewardsPunishments(String rewardsPunishments, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE rewardsPunishments1 = '" + rewardsPunishments + "' or rewardsPunishments2 = '" + rewardsPunishments + "' or rewardsPunishments3 = '" + rewardsPunishments + "';");
            while(rs.next()){
                if(rs.getString(9).contains(rewardsPunishments)){
                    System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                    System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
                }
            }
            
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public void searchByProgrammingLanguage(String programmingLanguage, String userID, String password){
        logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE programmingLanguage LIKE '%" + programmingLanguage +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14)));
            }
            
            rs.close();
            stmt.close();
        }catch (ClassNotFoundException e){
          System.out.println("ClassNotFoundException:" + e.getMessage());
        }catch (SQLException e){
          System.out.println("SQLException:" + e.getMessage());
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    public boolean Login(String userID, String password){
    	boolean login = false;
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            login = true;
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return login;
    }
    
    public boolean Authority(String userID, String password){
    	boolean authority = false;
    	try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(url, userID, password);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT authority FROM users WHERE emp_id =" + userID + ";");
            rs.next();
            authority = rs.getBoolean("authority");
            rs.close();
            stmt.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return authority;
    }
    

}
