import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Company {
    static Logger logger = Logger.getLogger(Company.class.getName());
    
    /**
    *従業員の登録
    */
    public void addStaff(Staff staff,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            stmt.executeUpdate(insetQuery(staff));
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
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
    
    /**
    *従業員の削除
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
    *従業員の更新
    */
    public void updateStaff(int updateID,Staff staff,Statement stmt) {
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
	    stmt.executeUpdate("UPDATE emp SET "
        			+ "emp_id = " + staff.getEmp_Id()
        			+ "emp_name = " + staff.getEmp_name()
        			+ "emp_gender = " + staff.getEmp_gender()
        			+ "birth = " + staff.getBirth()
        			+ "position = " + staff.getPosition()
        			+ "assignment = " + staff.getAssignment()
        			+ "yearsWorked = " + staff.getYearsWorked()
        			+ "certificate1 = " + staff.getCertificate().get(0)
        			+ "certificate2 = " + staff.getCertificate().get(1)
        			+ "certificate3 = " + staff.getCertificate().get(2)
        			+ "awardsPunishments1 = " + staff.getAwardsPunisuments().get(0)
        			+ "awardsPunishments2 = " + staff.getAwardsPunisuments().get(1)
        			+ "awardsPunishments3 = " + staff.getAwardsPunisuments().get(2)
        			+ "programmingLanguage =" + staff.getProgrammingLanguage()
        			+ " WHERE emp_id LIKE '" + emp_id + "';" );
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
	
    /**
    従業員一覧
    */
    public static void employeeView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT emp_id,emp_name,emp_gender,position,assignment FROM emp;");
            System.out.println("iD    名前       性別  役職       所属");
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
    *個人情報一覧
    */
    public static void informationView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
            System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数" 
            	+ "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *アサインメント状況一覧
    */
    public static void assignmentView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT project.pj_id,project.pj_name,count(*) AS nofEmp FROM projectHistory " 
              + "LEFT JOIN project ON projectHistory.pj_id = project.pj_id GROUP BY pj_id;");
            System.out.println("ProjcetiD    Project名         人数");
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
    *従業員IDで検索
    */
    public void searchByEmp_id(int emp_id,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_id LIKE '%" + emp_id +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *従業員名で検索
    */
    public void searchByEmp_name(String emp_name,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_name LIKE '%" + emp_name +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *性別で検索
    */
    public void searchByEmp_gender(char emp_gender,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_gender LIKE '%" + emp_gender +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *誕生日で検索
    */
    public void searchByBirth(String birth,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE birth LIKE '%" + birth +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *役職で検索
    */
    public void searchByPosition(String position,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE position LIKE '%" + position +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *アサインメントで検索
    */
    public void searchByAssignment(String assignment,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE assignment LIKE '%" + assignment +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *勤務年数で検索
    */
    public void searchByYearsWorked(int yearsWorked,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE yearsWorked LIKE '%" + yearsWorked +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *資格で検索
    */
    public void searchByCertificate(String certificate,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE certificate1 = '" + certificate + "' or certificate2 = '" + 
              certificate + "' or certificate3 = '" + certificate + "';");
            while(rs.next()){
                if(rs.getString(8).contains(certificate)){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数 " 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *賞罰で検索
    */
    public void searchByAwardsPunishments(String awardsPunishments,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE awardsPunishments1 = '" + awardsPunishments + "' or awardsPunishments2 = '" 
              + awardsPunishments + "' or awardsPunishments3 = '" + awardsPunishments + "';");
            while(rs.next()){
                if(rs.getString(9).contains(awardsPunishments)){
                    System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数" 
                      + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
    *プログラミング言語で検索
    */
    public void searchByProgrammingLanguage(String programmingLanguage,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE programmingLanguage LIKE '%" + programmingLanguage +"%';");
            while(rs.next()){
                System.out.println("iD   名前       性別 生年月日   役職       所属       勤務年数" 
                  + "資格1     資格2     資格3     賞罰1        賞罰2        賞罰3        プログラミング言語");
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
  
    public void exitID(int emp_id,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exitID = 0;
        try {
            ResultSet rs = stmt.executeQuery("SELECT count(*) AS exitID FROM emp WHERE emp_id LIKE '%" + emp_id +"%' GROUP BY emp_id;");
            rs.next();
            exitID = rs.getInt("exitID");
            rs.close();
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    public boolean Login(Statement stmt) throws Exception{
    	boolean login = false;
    	try {
            login = true;
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return login;
    }
    
　　/**
    *権限の有無
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
    

}
