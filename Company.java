import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class Company {
    static Logger logger = Logger.getLogger(StaffManagement.class.getName());
    
    /**
    *è]ã∆àıÇÃìoò^
    */
    public void addStaff(Staff staff,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println(staff.getCertificate().size());
        System.out.println(staff.getAwardsPunishments().size());
        try {
            if(staff.getCertificate().size() == 0){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery00(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery01(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery02(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery03(staff));
            	}
            }else if(staff.getCertificate().size() == 1){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery10(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery11(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery12(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery13(staff));
            	}
            }else if(staff.getCertificate().size() == 2){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery20(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery21(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery22(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery23(staff));
            	}
            }else if(staff.getCertificate().size() == 3){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery30(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery31(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery32(staff));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(insertQuery33(staff));
            	}
            }
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    *è]ã∆àıÇÃçÌèú
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
    *è]ã∆àıÇÃçXêV
    */
    public void updateStaff(int updateID,Staff staff,Statement stmt) {
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            if(staff.getCertificate().size() == 0){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery00(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery01(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery02(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery03(staff,updateID));
            	}
            }else if(staff.getCertificate().size() == 1){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery10(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery11(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery12(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery13(staff,updateID));
            	}
            }else if(staff.getCertificate().size() == 2){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery20(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery21(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery22(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery23(staff,updateID));
            	}
            }else if(staff.getCertificate().size() == 3){
            	if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery30(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery31(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery32(staff,updateID));
            	}else if(staff.getAwardsPunishments().size() == 0){
            		stmt.executeUpdate(updateQuery33(staff,updateID));
            	}
            }
        }catch (Exception e){
          System.out.println("Exception:" + e.getMessage());
        }
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
	
    /**
    *è]ã∆àıàÍóó
    */
    public static void employeeView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT emp_id,emp_name,emp_gender,position,assignment FROM emp;");
            System.out.println("iD    ñºëO       ê´ï   ñêE       èäëÆ");
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
    *å¬êlèÓïÒàÍóó
    */
    public static void informationView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp;");
            System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî" 
            	             + " éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ÉAÉTÉCÉìÉÅÉìÉgèÛãµàÍóó
    */
    public static void assignmentView(Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT project.pj_id,project.pj_name,count(*) AS nofEmp FROM projectHistory " 
                         + "LEFT JOIN project ON projectHistory.pj_id = project.pj_id GROUP BY pj_id;");
            System.out.println("ProjcetiD    Projectñº         êlêî");
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
    *è]ã∆àıIDÇ≈åüçı
    */
    public void searchByEmp_id(int emp_id,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_id LIKE '%" + emp_id +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *è]ã∆àıñºÇ≈åüçı
    */
    public void searchByEmp_name(String emp_name,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_name LIKE '%" + emp_name +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ê´ï Ç≈åüçı
    */
    public void searchByEmp_gender(char emp_gender,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE emp_gender LIKE '%" + emp_gender +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *íaê∂ì˙Ç≈åüçı
    */
    public void searchByBirth(String birth,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE birth LIKE '%" + birth +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ñêEÇ≈åüçı
    */
    public void searchByPosition(String position,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE position LIKE '%" + position +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ÉAÉTÉCÉìÉÅÉìÉgÇ≈åüçı
    */
    public void searchByAssignment(String assignment,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE assignment LIKE '%" + assignment +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ãŒñ±îNêîÇ≈åüçı
    */
    public void searchByYearsWorked(int yearsWorked,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE yearsWorked LIKE '%" + yearsWorked +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *éëäiÇ≈åüçı
    */
    public void searchByCertificate(String certificate,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE certificate1 = '" + certificate + "' or certificate2 = '" + 
              certificate + "' or certificate3 = '" + certificate + "';");
            while(rs.next()){
                if(rs.getString(8).contains(certificate)){
                    System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî " 
                                     + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *è‹î±Ç≈åüçı
    */
    public void searchByAwardsPunishments(String awardsPunishments,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE awardsPunishments1 = '" + awardsPunishments + "' or awardsPunishments2 = '" 
              + awardsPunishments + "' or awardsPunishments3 = '" + awardsPunishments + "';");
            while(rs.next()){
                if(rs.getString(9).contains(awardsPunishments)){
                    System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî" 
                                     + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *ÉvÉçÉOÉâÉ~ÉìÉOåæåÍÇ≈åüçı
    */
    public void searchByProgrammingLanguage(String programmingLanguage,Statement stmt) throws Exception{
        logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        try {
            ResultSet rs = stmt.executeQuery("SELECT * FROM emp WHERE programmingLanguage LIKE '%" + programmingLanguage +"%';");
            while(rs.next()){
                System.out.println("iD   ñºëO       ê´ï  ê∂îNåéì˙   ñêE       èäëÆ       ãŒñ±îNêî" 
                                 + "éëäi1     éëäi2     éëäi3     è‹î±1        è‹î±2        è‹î±3        ÉvÉçÉOÉâÉ~ÉìÉOåæåÍ");
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
    *å†å¿ÇÃóLñ≥
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
    *ìoò^
    *éëäi0å¬§è‹î±0å¬
    */
    public static String insertQuery00(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }

    /**
    *ìoò^
    *éëäi1å¬§è‹î±0å¬
    */
    public static String insertQuery10(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi2å¬§è‹î±0å¬
    */
    public static String insertQuery20(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi3å¬§è‹î±0å¬
    */
    public static String insertQuery30(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getCertificate().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi0å¬§è‹î±1å¬
    */
    public static String insertQuery01(Staff staff){
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
               + "awardsPunishments1,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi1å¬§è‹î±1å¬
    */
    public static String insertQuery11(Staff staff){
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
               + "awardsPunishments1,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi2å¬§è‹î±1å¬
    */
    public static String insertQuery21(Staff staff){
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
               + "awardsPunishments1,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi3å¬§è‹î±1å¬
    */
    public static String insertQuery31(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getCertificate().get(2) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi0å¬§è‹î±2å¬
    */
    public static String insertQuery02(Staff staff){
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
               + "awardsPunishments1,"
               + "awardsPunishments2,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi1å¬§è‹î±2å¬
    */
    public static String insertQuery12(Staff staff){
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
               + "awardsPunishments1,"
               + "awardsPunishments2,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi2å¬§è‹î±2å¬
    */
    public static String insertQuery22(Staff staff){
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
               + "awardsPunishments1,"
               + "awardsPunishments2,"
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi3å¬§è‹î±2å¬
    */
    public static String insertQuery32(Staff staff){
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
               + "programmingLanguage) ";
        insert = insert + "VALUES("
               + staff.getEmp_id() + ",'"
               + staff.getEmp_name()+ "','"
               + staff.getEmp_gender() + "','"
               + strBirth + "','"
               + staff.getPosition() + "','"
               + staff.getAssignment() + "',"
               + staff.getYearsWorked() + ",'"
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getCertificate().get(2) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi0å¬§è‹î±3å¬
    */
    public static String insertQuery03(Staff staff){
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
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getAwardsPunishments().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi1å¬§è‹î±3å¬
    */
    public static String insertQuery13(Staff staff){
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
               + staff.getCertificate().get(0) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getAwardsPunishments().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi2å¬§è‹î±3å¬
    */
    public static String insertQuery23(Staff staff){
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
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getAwardsPunishments().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ìoò^
    *éëäi3å¬§è‹î±3å¬
    */
    public static String insertQuery33(Staff staff){
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
               + staff.getCertificate().get(0) + "','"
               + staff.getCertificate().get(1) + "','"
               + staff.getCertificate().get(2) + "','"
               + staff.getAwardsPunishments().get(0) + "','"
               + staff.getAwardsPunishments().get(1) + "','"
               + staff.getAwardsPunishments().get(2) + "','"
               + staff.getProgrammingLanguage() + "');";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return insert;
    }
    
    /**
    *ïœçX
    *éëäi0å¬§è‹î±0å¬
    */
    public static String updateQuery00(Staff staff, int updateID){
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
                      + ",programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }

    /**
    *ïœçX
    *éëäi1å¬§è‹î±0å¬
    */
    public static String updateQuery10(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi2å¬§è‹î±0å¬
    */
    public static String updateQuery20(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi3å¬§è‹î±0å¬
    */
    public static String updateQuery30(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',certificate3 = '" + staff.getCertificate().get(2)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi0å¬§è‹î±1å¬
    */
    public static String updateQuery01(Staff staff, int updateID){
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
                      + ",awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi1å¬§è‹î±1å¬
    */
    public static String updateQuery11(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi2å¬§è‹î±1å¬
    */
    public static String updateQuery21(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi3å¬§è‹î±1å¬
    */
    public static String updateQuery31(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',certificate3 = '" + staff.getCertificate().get(2)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi0å¬§è‹î±2å¬
    */
    public static String updateQuery02(Staff staff, int updateID){
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
                      + ",awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi1å¬§è‹î±2å¬
    */
    public static String updateQuery12(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi2å¬§è‹î±2å¬
    */
    public static String updateQuery22(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi3å¬§è‹î±2å¬
    */
    public static String updateQuery32(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',certificate3 = '" + staff.getCertificate().get(2)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi0å¬§è‹î±3å¬
    */
    public static String updateQuery03(Staff staff, int updateID){
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
                      + ",awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',awardsPunishments3 = '" + staff.getAwardsPunishments().get(2)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi1å¬§è‹î±3å¬
    */
    public static String updateQuery13(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',awardsPunishments3 = '" + staff.getAwardsPunishments().get(2)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi2å¬§è‹î±3å¬
    */
    public static String updateQuery23(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',awardsPunishments3 = '" + staff.getAwardsPunishments().get(2)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }
    
    /**
    *ïœçX
    *éëäi3å¬§è‹î±3å¬
    */
    public static String updateQuery33(Staff staff, int updateID){
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
                      + ",certificate1 = '" + staff.getCertificate().get(0)
                      + "',certificate2 = '" + staff.getCertificate().get(1)
                      + "',certificate3 = '" + staff.getCertificate().get(2)
                      + "',awardsPunishments1 = '" + staff.getAwardsPunishments().get(0)
                      + "',awardsPunishments2 = '" + staff.getAwardsPunishments().get(1)
                      + "',awardsPunishments3 = '" + staff.getAwardsPunishments().get(2)
                      + "',programmingLanguage = '" + staff.getProgrammingLanguage()
                      + "' WHERE emp_id LIKE '" + updateID + "';";
        logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return update;
    }

}
