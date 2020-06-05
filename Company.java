import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Company {
    static Logger logger = Logger.getLogger(Company.class.getName());
    
    public static String insetQuery(Staff staff){
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        String insertOther = "INSERT INTO emp(emp_id,emp_name,emp_gender,birth,position,assignment,yearsWorked";
        String insertCertificate = "";
        String insertAwardsPunishments = "";
        String strBirth = new SimpleDateFormat("yyyy-MM-dd").format(staff.getBirth());
        String insertValueOther = "VALUES(" + staff.getEmp_id() + ",'" + staff.getEmp_name() + "','" + staff.getEmp_gender() + 
          "','" + strBirth + "','" + staff.getPosition() + "','" + staff.getAssignment() + "'," + staff.getYearsWorked();
        String insertValueCertificate = "";
        String insertValueAwardsPunishments = "";
        for(int j = 0; j<staff.getCertificate().size(); j++){
            if(!(staff.getCertificate().get(j).equals(""))){
                insertCertificate = ",certificate" + (j + 1);
                insertValueCertificate += insertValueCertificate + ",'" + staff.getCertificate().get(j) + "'"; 
            }
        }
        for(int j = 0; j<staff.getAwardsPunishments().size(); j++){
            if(!(staff.getAwardsPunishments().get(j).equals(""))){
                insertAwardsPunishments = ",awardsPunishments" + (j + 1);
                insertValueAwardsPunishments += insertValueAwardsPunishments + ",'" + staff.getAwardsPunishments().get(j) + "'";
            }
        }
        String insert = insertOther + insertCertificate + insertAwardsPunishments +",programmingLanguage) ";
        String insertValue = insertValueOther + insertValueCertificate 
          + insertValueAwardsPunishments + ",'" + staff.getProgrammingLanguage() + "');";
        String query = insert + insertValue;
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return query;
    }
    
    //]‹Æˆõ‚Ì“o˜^
    public void addStaff(Staff staff,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            stmt.executeUpdate(insetQuery(staff));
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //]‹Æˆõ‚Ìíœ
    public void deleteStaff(int delete,String userID,String password,Statement stmt) throws Exception{
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
    
    //]‹Æˆõ‚ÌXV
    public void updateStaff(int updateID,Staff staff,String userID,String password,Statement stmt) {
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            deleteStaff(updateID,userID,password,stmt);
            addStaff(staff,userID,password,stmt);
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    public static void employeeView(String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT emp_id,emp_name,emp_gender,position,assignment FROM emp;");
            System.out.println("iD    –¼‘O       «•Ê  –ðE       Š‘®");
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
    
    public static void informationView(String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
            System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” Ž‘Ši1     Ž‘Ši2     Ž‘Ši3"
               + "     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public static void assignmentView(String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT project.pj_id,project.pj_name,count(*) AS nofEmp FROM projectHistory " 
              + "LEFT JOIN project ON projectHistory.pj_id = project.pj_id GROUP BY pj_id;");
            System.out.println("ProjcetiD    Project–¼         l”");
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
    
    public void searchByEmp_id(int emp_id,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_id LIKE '%" + emp_id +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByEmp_name(String emp_name,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_name LIKE '%" + emp_name +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByEmp_gender(char emp_gender,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_gender LIKE '%" + emp_gender +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByBirth(String birth,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE birth LIKE '%" + birth +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByPosition(String position,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE position LIKE '%" + position +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByAssignment(String assignment,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE assignment LIKE '%" + assignment +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByYearsWorked(int yearsWorked,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE yearsWorked LIKE '%" + yearsWorked +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByCertificate(String certificate,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE certificate1 = '" + certificate + "' or certificate2 = '" + 
              certificate + "' or certificate3 = '" + certificate + "';");
            while(rs.next()){
                if(rs.getString(8).contains(certificate)){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” " 
                  + "Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByAwardsPunishments(String awardsPunishments,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE awardsPunishments1 = '" + awardsPunishments + "' or awardsPunishments2 = '" 
              + awardsPunishments + "' or awardsPunishments3 = '" + awardsPunishments + "';");
            while(rs.next()){
                if(rs.getString(9).contains(awardsPunishments)){
                    System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public void searchByProgrammingLanguage(String programmingLanguage,String userID,String password,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE programmingLanguage LIKE '%" + programmingLanguage +"%';");
            while(rs.next()){
                System.out.println("iD   –¼‘O       «•Ê ¶”NŒŽ“ú   –ðE       Š‘®       ‹Î–±”N” Ž‘Ši1     Ž‘Ši2     Ž‘Ši3     Ü”±1        Ü”±2        Ü”±3        ƒvƒƒOƒ‰ƒ~ƒ“ƒOŒ¾Œê");
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
    
    public boolean Login(String userID,String password,Statement stmt) throws Exception{
    	boolean login = false;
    	try {
            login = true;
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return login;
    }
    
    public boolean Authority(String userID,String password,Statement stmt) throws Exception{
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
    

}
