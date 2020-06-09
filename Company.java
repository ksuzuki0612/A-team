import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Company {
    static Logger logger = Logger.getLogger(StaffManagement.class.getName());
    
    /**
    *]ÆõÌo^
    */
    public void addStaff(Staff staff,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            stmt.executeUpdate(insertQuery(staff));
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *]ÆõÌí
    */
    public void deleteStaff(int delete,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try{
            String sql1 = "DELETE FROM emp WHERE emp_id = " + delete + ";";
            String sql2 = "DELETE FROM projecthistory WHERE emp_id = " + delete + ";";
            String sql3 = "DELETE FROM users WHERE emp_id = " + delete + ";";
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *]ÆõÌXV
    */
    public void updateStaff(int updateID,Staff staff,Statement stmt) {
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            stmt.executeUpdate(updateQuery(staff,updateID));
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
	
    /**
    *]Æõê
    */
    public static void employeeView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT emp_id,emp_name,emp_gender,position,assignment FROM emp;");
            System.out.println("iD    ¼O       «Ê  ðE       ®");
            while(rs.next()){
                int emp_id = rs.getInt("emp_id");
                String emp_name = rs.getString("emp_name");
                String emp_gender = rs.getString("emp_gender");
                String position = rs.getString("position");
                String assignment = rs.getString("assignment");
                System.out.println(String.format("%-5s %-10s %-5s %-10s %-10s",emp_id,emp_name,emp_gender,position,assignment));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
   
    /**
    *Âlîñê
    */
    public static void informationView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
            System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N" 
            	             + " i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
            while(rs.next()){
	            System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),
                rs.getInt(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),
                rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *ATCgóµê
    */
    public static void assignmentView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT project.pj_id,project.pj_name,count(*) AS nofEmp FROM projectHistory " 
                         + "LEFT JOIN project ON projectHistory.pj_id = project.pj_id GROUP BY pj_id;");
            System.out.println("ProjcetiD    Project¼         l");
            while(rs.next()){
                int pj_id = rs.getInt("pj_id");
                String pj_name = rs.getString("pj_name");
                int nofEmp = rs.getInt("nofEmp");
                System.out.println(String.format("%-12s %-17s %-5s",pj_id,pj_name,nofEmp));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *]ÆõIDÅõ
    */
    public void searchByEmp_id(int emp_id,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_id LIKE '%" + emp_id +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *]Æõ¼Åõ
    */
    public void searchByEmp_name(String emp_name,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_name LIKE '%" + emp_name +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *«ÊÅõ
    */
    public void searchByEmp_gender(char emp_gender,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_gender LIKE '%" + emp_gender +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *a¶úÅõ
    */
    public void searchByBirth(String birth,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE birth LIKE '%" + birth +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *ðEÅõ
    */
    public void searchByPosition(String position,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE position LIKE '%" + position +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *ATCgÅõ
    */
    public void searchByAssignment(String assignment,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE assignment LIKE '%" + assignment +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *Î±NÅõ
    */
    public void searchByYearsWorked(int yearsWorked,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE yearsWorked LIKE '%" + yearsWorked +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *iÅõ
    */
    public void searchByCertificate(String certificate,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE certificate1 = '" + certificate + "' or certificate2 = '" + 
              certificate + "' or certificate3 = '" + certificate + "';");
            while(rs.next()){
                if(rs.getString(8).contains(certificate)){
                    System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N " 
                                     + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                    System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                	rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
                }
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *Ü±Åõ
    */
    public void searchByAwardsPunishments(String awardsPunishments,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE awardsPunishments1 = '" + awardsPunishments + "' or awardsPunishments2 = '" 
              + awardsPunishments + "' or awardsPunishments3 = '" + awardsPunishments + "';");
            while(rs.next()){
                if(rs.getString(9).contains(awardsPunishments)){
                    System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N" 
                                     + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                    System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                    rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                	rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
                }
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *vO~O¾êÅõ
    */
    public void searchByProgrammingLanguage(String programmingLanguage,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE programmingLanguage LIKE '%" + programmingLanguage +"%';");
            while(rs.next()){
                System.out.println("iD   ¼O       «Ê ¶Nú   ðE       ®       Î±N" 
                                 + "i1     i2     i3     Ü±1        Ü±2        Ü±3        vO~O¾ê");
                System.out.println(String.format("%-4s %-10s %-4s %-10s %-10s %-10s %-8s %-9s %-9s %-9s %-12s %-12s %-12s %-10s",
                rs.getInt(1),rs.getString(2),rs.getString(3),rs.getDate(4),rs.getString(5),rs.getString(6),rs.getInt(7),
                rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14)));
            }
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * ÀÌL³
    */
    public boolean Authority(String userID, Statement stmt) throws Exception{
    	boolean authority = false;
    	try {
            ResultSet rs = stmt.executeQuery("SELECT authority FROM users WHERE emp_id =" + userID + ";");
            rs.next();
            authority = rs.getBoolean("authority");
            rs.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return authority;
    }
    
    /**
    *o^
    */
    public static String insertQuery(Staff staff){
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        String strBirth = new SimpleDateFormat("yyyy-MM-dd").format(staff.getBirth());
        String insert = "INSERT INTO emp("
               + "emp_id,"
               + "emp_name,"
               + "emp_gender,"
               + "birth,"
               + "position,"
               + "assignment,"
               + "yearsWorked,"
               + "certificate1,"
               + "certificate2,"
               + "certificate3,"
               + "awardsPunishments1,"
               + "awardsPunishments2,"
               + "awardsPunishments3,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate1() + "','"
               + staff.getCertificate2() + "','"
               + staff.getCertificate3() + "','"
               + staff.getAwardsPunishments1() + "','"
               + staff.getAwardsPunishments2() + "','"
               + staff.getAwardsPunishments3() + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ÏX
    */
    public static String updateQuery(Staff staff, int updateID){
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        String strBirth = new SimpleDateFormat("yyyy-MM-dd").format(staff.getBirth());
        String update = "UPDATE emp SET "
                      + "emp_id = " + staff.getEmp_id()
                      + ",emp_name = '" + staff.getEmp_name()
                      + "',emp_gender = '" + staff.getEmp_gender()
                      + "',birth = '" + strBirth
                      + "',position = '" + staff.getPosition()
                      + "',assignment = '" + staff.getAssignment()
                      + "',yearsWorked = " + staff.getYearsWorked()
                      + ",certificate1 = '" + staff.getCertificate1()
                      + "',certificate2 = '" + staff.getCertificate2()
                      + "',certificate3 = '" + staff.getCertificate3()
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments1()
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments2()
                      + "',awardsPunishments3 = '" + staff.getAwardsPunishments3()
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }

}
