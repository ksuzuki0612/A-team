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
    static Logger logger = Logger.getLogger(MainHRM.class.getName());
    private static Company company = new Company();
    static final String mysqlURL = "jdbc:mysql://localhost:3306/employee?useUnicode=true&useJDBC"
                                 + "CompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    
    public static void main(final String[] args) throws Exception{
        FileHandler handler = new FileHandler("log.txt"); 
        handler.setFormatter(new SimpleFormatter()); 
        logger.addHandler(handler); 
        // ���O���x���̐ݒ�
        logger.setLevel(Level.FINER);
        
        System.out.println("�]�ƈ�ID����͂��Ă�������");
        String userID = new java.util.Scanner(System.in).nextLine();
        System.out.println("�p�X���[�h����͂��Ă�������");
        String password = new java.util.Scanner(System.in).nextLine();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            Connection conn = DriverManager.getConnection(mysqlURL,userID,password);
            Statement stmt = conn.createStatement();
            if(company.Login(stmt) == true){
                if(company.Authority(userID,stmt) == true){
                    managerMenu(stmt);
                }else{
                	userMenu(stmt);
                }
            }
            stmt.close();
        }catch (Exception e){
            System.out.println("Exception:" + e.getMessage());
        }
    }
    
    //���j���[�I��
    public static void managerMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[���j���[]");
            System.out.println("1:�]�ƈ��o�^");
            System.out.println("2.�]�ƈ��폜");
            System.out.println("3.�]�ƈ��X�V");
            System.out.println("4.�]�ƈ��ꗗ");
            System.out.println("5.�l���ꗗ");
            System.out.println("6.�A�T�C�������g�󋵈ꗗ");
            System.out.println("7.����");
            System.out.println("8.�I��\n");
            int number = new java.util.Scanner(System.in).nextInt();
            switch (number) {
                case 1:
                    addStaff(stmt);
                    break;
                case 2:
                    deleteStaff(stmt);
                    break;
                case 3:
                    updateStaff(stmt);
                    break;
                case 4:
                    System.out.println("�]�ƈ��ꗗ");
                    company.employeeView(stmt);
                    break;
                case 5:
                    company.informationView(stmt);
                    break;
                case 6:
                    company.assignmentView(stmt);
                    break;
                case 7:
                    searchStaffs(stmt);
                    break;
                case 8:
                exid = 1; 
                    break;
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //���j���[�I��
    public static void userMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[���j���[]");
            System.out.println("1:�]�ƈ��ꗗ");
            System.out.println("2.�l���ꗗ");
            System.out.println("3.�A�T�C�������g�󋵈ꗗ");
            System.out.println("4.����");
            System.out.println("5.�I��");
            int number = new java.util.Scanner(System.in).nextInt();
            switch (number) {
                case 1:
                    System.out.println("�]�ƈ��ꗗ");
                    company.employeeView(stmt);
                    break;
                case 2:
                    company.informationView(stmt);
                    break;
                case 3:
                    company.assignmentView(stmt);
                    break;
                case 4:
                    searchStaffs(stmt);
                    break;
                case 5:
                exid = 1; 
                    break;
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }

    //�]�ƈ��̓o�^
    public static void addStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.addStaff(data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //�]�ƈ�ID�̓���
    public static int inputID(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�]�ƈ�ID����͂��Ă�������");
        int emp_id = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_id;
    }
    
    //�]�ƈ����̓���
    public static String inputName(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�]�ƈ�������͂��Ă�������");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_name;
    }
    
    //���ʂ̓���
    public static char inputGender(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("���ʂ�M�i�j���j�AF�i�����j�AX�i���̑��j����I�������͂��Ă�������");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_gender;
    }
    
    //���N�����̓���
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
    
    //��E�̓���
    public static String inputPosition(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("��E����͂��Ă�������");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return position;
    }
    
    //�����̓���
    public static String inputAssignment(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("��������͂��Ă�������");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return assignment;
    }
    
    //�Ζ��N���̓���
    public static int inputYearsWorked(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�Ζ��N������͂��Ă�������");
        int yearsWorked = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return yearsWorked;
    }
    
    //���i�̓���
    public static List<String> inputCertificate(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int i=0;
        List<String> certificate = new ArrayList<String>();
        for(int j = 0 ; j < 3 ; j++){
            System.out.println("���i����͂��Ă�������");
            String strCertificate = new java.util.Scanner(System.in).nextLine();
            certificate.add(strCertificate);
            System.out.println("�I������ꍇ��1�������Ă��������B");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 3;
            }
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return certificate;
    }
    
    //�ܔ��̓���
    public static List<String> inputAwardsPunishments(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int i=0;
        List<String> awardsPunishments = new ArrayList<String>();
        for(int j = 0 ; j < 3 ; j++){
            System.out.println("�ܔ�����͂��Ă�������");
            String strAwardsPunishments = new java.util.Scanner(System.in).nextLine();
            awardsPunishments.add(strAwardsPunishments);
            System.out.println("�I������ꍇ��1�������Ă��������B");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 3;
            }
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return awardsPunishments;
    }
    
    //�g�p����̓���
    public static String inputProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�g�p�������͂��Ă�������");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    //�]�ƈ��̍폜
    public static void deleteStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("�폜����]�ƈ���ID����͂��Ă�������");
        int delete = new java.util.Scanner(System.in).nextInt();
        company.deleteStaff(delete,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //�]�ƈ��̍X�V
    public static void updateStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean exist = false;
        System.out.println("�ύX����]�ƈ���ID����͂��Ă�������");
        int updateID = new java.util.Scanner(System.in).nextInt();
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.updateStaff(updateID,data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }

    //�]�ƈ��̌���
    public static void searchStaffs(Statement stmt)throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("��������]�ƈ��̍��ڂ���͂��Ă��������B");
        System.out.println("1:�Ј�ID\n2:���O\n3:����\n4:���N����\n5:��E\n6:����\n7:�Ζ��N��\n8:���i\n9:�ܔ�\n10:�v���O���~���O����");
        int selectedInformation = new java.util.Scanner(System.in).nextInt();
        switch(selectedInformation){
            case 1:
                System.out.println("�Ј�ID����͂��Ă��������B");
                int emp_id = new java.util.Scanner(System.in).nextInt();
                company.searchByEmp_id(emp_id,stmt);
                break;
            case 2:
                System.out.println("�Ј��̖��O����͂��Ă��������B");
                String emp_name = new java.util.Scanner(System.in).nextLine();
                company.searchByEmp_name(emp_name,stmt);
                break;
            case 3:
                System.out.println("���ʂ���͂��Ă��������B");
                String str = new java.util.Scanner(System.in).nextLine();
                char emp_gender = str.charAt(0);
                company.searchByEmp_gender(emp_gender,stmt);
                break;
            case 4:
                System.out.println("���N��������͂��Ă��������B��1993-08-19");
                String birth = new java.util.Scanner(System.in).nextLine();
                company.searchByBirth(birth,stmt);
                break;
            case 5:
                System.out.println("��E����͂��Ă��������B");
                String position = new java.util.Scanner(System.in).nextLine();
                company.searchByPosition(position,stmt);
                break;
            case 6:
                System.out.println("�������͂��Ă��������B");
                String assignment = new java.util.Scanner(System.in).nextLine();
                company.searchByAssignment(assignment,stmt);
                break;
            case 7:
                System.out.println("�Ζ��N������͂��Ă��������B");
                int yearsWorked = new java.util.Scanner(System.in).nextInt();
                company.searchByYearsWorked(yearsWorked,stmt);
                break;
            case 8:
                System.out.println("���i����͂��Ă��������B");
                String certificate = new java.util.Scanner(System.in).nextLine();
                company.searchByCertificate(certificate,stmt);
                break;
            case 9:
                System.out.println("�ܔ�����͂��Ă��������B");
                String awardsPunishments = new java.util.Scanner(System.in).nextLine();
                company.searchByAwardsPunishments(awardsPunishments,stmt);
                break;
            case 10:
                System.out.println("�v���O���~���O�������͂��Ă��������B");
                String programmingLanguage = new java.util.Scanner(System.in).nextLine();
                company.searchByProgrammingLanguage(programmingLanguage,stmt);
                break;
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
}