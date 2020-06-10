import java.util.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class StaffManagement {
    static Logger logger = Logger.getLogger(StaffManagement.class.getName());
    private static Company company = new Company();
    static final String mysqlURL = "jdbc:mysql://localhost:3306/employee?useUnicode=true&useJDBC"
                                 + "CompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public static void main(final String[] args) throws Exception{
        FileHandler handler = new FileHandler("log.txt"); 
        handler.setFormatter(new SimpleFormatter()); 
        logger.addHandler(handler); 
        // ���O���x���̐ݒ�
        logger.setLevel(Level.FINER);
        String logout = "";
        do{
            try {
                System.out.println("�]�ƈ�ID����͂��Ă�������");
                int userID = new java.util.Scanner(System.in).nextInt();
                String id = "" + userID;
                System.out.println("�p�X���[�h����͂��Ă�������");
                String password = new java.util.Scanner(System.in).nextLine();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection(mysqlURL,id,password);
                Statement stmt = conn.createStatement();
                if(company.Authority(userID,stmt) == true){
                    managerMenu(stmt);
                }else{
                	userMenu(stmt);
                }
                stmt.close();
            }catch (Exception e){
                System.out.println("�F�؃G���[���������܂����B\n���[�U�[���܂��̓p�X���[�h���s���ł��B");
            }
            System.out.println("�I������ꍇ��1����͂��Ă�������");
            logout = new java.util.Scanner(System.in).nextLine();
        }while(!logout.equals("1"));
    }
    
    /**
    * �Ǘ��҃��j���[
    */
    public static void managerMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[���j���[]");
            System.out.println("1:�ҏW���j���[");
            System.out.println("2.�ꗗ�\�����j���[");
            System.out.println("3.�������j���[");
            System.out.println("4.�߂�");
            String number = new java.util.Scanner(System.in).nextLine();
            boolean exit = false;
            switch (number) {
                case "1":
                    aditStaff(stmt);
                    exit = true;
                    break;
                case "2":
                    viewStaff(stmt);
                    exit = true;
                    break;
                case "3":
                    searchStaffs(stmt);
                    exit = true;
                    break;
                case "4":
                    exid = 1; 
                    exit = true;
                    break;
            }
            if(exit == false){
            	System.out.println("�I�����ꂽ���j���[��������܂���1");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    
    /**
    * ��ʂ̃��j���[
    */
    public static void userMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[���j���[]");
            System.out.println("1.�ꗗ�\�����j���[");
            System.out.println("2.�������j���[");
            System.out.println("3.�߂�");
            String number = new java.util.Scanner(System.in).nextLine();
            boolean exit = false;
            switch (number) {
                case "1":
                    exit = true;
                    viewStaff(stmt);
                    break;
                case "2":
                    exit = true;
                    searchStaffs(stmt);
                    break;
                case "3":
                    exit = true;
                    exid = 1; 
                    break;
            }
            if(exit == false){
            	System.out.println("�I�����ꂽ���j���[��������܂���2");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �ҏW���j���[
    */
    public static void aditStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[���j���[]");
            System.out.println("1:�]�ƈ��o�^");
            System.out.println("2.�]�ƈ��폜");
            System.out.println("3.�]�ƈ��X�V");
            System.out.println("4.�߂�\n");
            String number = new java.util.Scanner(System.in).nextLine();
            boolean exit = false;
            switch (number) {
                case "1":
                    exit = true;
                    addStaff(stmt);
                    break;
                case "2":
                    exit = true;
                    deleteStaff(stmt);
                    break;
                case "3":
                    exit = true;
                    updateStaff(stmt);
                    break;
                case "4":
                    exit = true;
                    exid = 1; 
                    break;
            }
            if(exit == false){
            	System.out.println("�I�����ꂽ���j���[��������܂���3");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �]�ƈ��̓o�^
    */
    public static void addStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        Staff data = new Staff(
        	inputID(),
        	inputName(),
        	inputGender(),
        	inputBirth(),
        	inputPosition(),
        	inputAssignment(),
            inputYearsWorked(),
            inputCertificate(),
            inputCertificate(),
            inputCertificate(),
            inputAwardsPunishments(),
            inputAwardsPunishments(),
            inputAwardsPunishments(),
            inputProgrammingLanguage());
        company.addStaff(data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �]�ƈ�ID�̓���
    */
    public static int inputID(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean dateCheck = false;
        int id = 0;
        do {
            System.out.println("�]�ƈ�ID����͂��Ă�������");
            String emp_id = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                id = Integer.parseInt(emp_id);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("���������͂��Ă�������");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return id;
    }
    
    /**
    * �]�ƈ����̓���
    */
    public static String inputName(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�]�ƈ�������͂��Ă�������");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_name;
    }
    
    /**
    * ���ʂ̓���
    */
    
    public static char inputGender(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("���ʂ�M�i�j���j�AF�i�����j�AX�i���̑��j����I�������͂��Ă�������");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_gender;
    }
    
    /**
    * ���N�����̓���
    */
    public static Date inputBirth(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean dateCheck = false;
        Date Birth = new Date(1600705425827L);
        do {
            System.out.println("���N��������͂��Ă�������");
            String strBirth = new java.util.Scanner(System.in).nextLine();
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-mm-dd");
            try(FileWriter fw = new FileWriter("data.txt");){
                Date date = sdFormat.parse(strBirth);
                Birth = date;
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("���������͂��Ă�������");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return Birth;
    }
    
    /**
    * ��E�̓���
    */
    public static String inputPosition(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("��E����͂��Ă�������");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return position;
    }
    
    /**
    * �����̓���
    */
    public static String inputAssignment(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("��������͂��Ă�������");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return assignment;
    }
    
    /**
    * �Ζ��N���̓���
    */
    public static int inputYearsWorked(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean dateCheck = false;
        int year = 0;
        do {
            System.out.println("�Ζ��N������͂��Ă�������");
            String yearsWorked = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                year = Integer.parseInt(yearsWorked);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("���������͂��Ă�������");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return year;
    }
    
    /**
    * ���i�̓���
    */
    public static String inputCertificate(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("���i����͂��Ă�������");
        String strCertificate = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return strCertificate;
    }
    
    /**
    * �ܔ��̓���
    */
    public static String inputAwardsPunishments(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�ܔ�����͂��Ă�������");
        String strAwardsPunishments = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return strAwardsPunishments;
    }
    
    /**
    * �g�p����̓���
    */
    public static String inputProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�g�p�������͂��Ă�������");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    /**
    * �]�ƈ��̍폜
    */
    public static void deleteStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�폜����]�ƈ���ID����͂��Ă�������");
        String delete = new java.util.Scanner(System.in).nextLine();
        company.deleteStaff(delete,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �]�ƈ��̍X�V
    */
    public static void updateStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean exist = false;
        boolean dateCheck = false;
        int updateID = 0;
        do {
            System.out.println("�ύX����]�ƈ���ID����͂��Ă�������");
            String id = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                updateID = Integer.parseInt(id);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("���������͂��Ă�������");
            }
        }while(dateCheck == false);
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputCertificate(),inputCertificate(),inputAwardsPunishments(),
            inputAwardsPunishments(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.updateStaff(updateID,data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �ꗗ�\�����j���[
    */
    public static void viewStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            boolean exit = false;
            System.out.println("\n[���j���[]");
            System.out.println("1:�]�ƈ��ꗗ");
            System.out.println("2.�l���ꗗ");
            System.out.println("3.�A�T�C�������g�󋵈ꗗ");
            System.out.println("4.�߂�\n");
            String number = new java.util.Scanner(System.in).nextLine();
            switch (number) {
                case "1":
                    exit = true;
                    company.employeeView(stmt);
                    break;
                case "2":
                    company.informationView(stmt);
                    exit = true;
                    break;
                case "3":
                    company.assignmentView(stmt);
                    exit = true;
                    break;
                case "4":
                    exit = true;
                    exid = 1; 
                    break;
            }
            if(exit == false){
            	System.out.println("�I�����ꂽ���j���[��������܂���4");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * �]�ƈ��̌���
    */
    public static void searchStaffs(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            boolean exit = false;
            System.out.println("\n��������]�ƈ��̍��ڂ���͂��Ă��������B");
            System.out.println("1:�Ј�ID\n2:���O\n3:����\n4:���N����\n5:��E\n6:����\n7:�Ζ��N��\n8:���i\n9:�ܔ�\n10:�v���O���~���O����\n11:�߂�\n");
            String selectedInformation = new java.util.Scanner(System.in).nextLine();
            switch(selectedInformation){
                case "1":
                    exit = true;
                    company.searchByEmp_id(inputID(),stmt);
                    break;
                case "2":
                    exit = true;
                    company.searchByEmp_name(inputName(),stmt);
                    break;
                case "3":
                    exit = true;
                    company.searchByEmp_gender(inputGender(),stmt);
                    break;
                case "4":
                    exit = true;
                    company.searchByBirth(inputBirth(),stmt);
                    break;
                case "5":
                    exit = true;
                    company.searchByPosition(inputPosition(),stmt);
                    break;
                case "6":
                    exit = true;
                    company.searchByAssignment(inputAssignment(),stmt);
                    break;
                case "7":
                    exit = true;
                    company.searchByYearsWorked(inputYearsWorked(),stmt);
                    break;
                case "8":
                    exit = true;
                    company.searchByCertificate(inputCertificate(),stmt);
                    break;
                case "9":
                    exit = true;
                    company.searchByAwardsPunishments(inputAwardsPunishments(),stmt);
                    break;
                case "10":
                    exit = true;
                    company.searchByProgrammingLanguage(inputProgrammingLanguage(),stmt);
                    break;
                case "11":
                    exit = true;
                    exid = 1;
                    break;
            }
            if(exit == false){
                System.out.println("�I�����ꂽ���ڂ�����܂���");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
}
