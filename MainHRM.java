import java.util.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
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

public class MainHRM {
    static Logger logger = Logger.getLogger(MainHRM.class.getName());
    private static Company company = new Company();
    
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
        if(company.Login(userID, password) == true){
            if(company.Authority(userID, password) == true){
                managerMenu(userID, password);
            }else{
            	userMenu(userID, password);
            }
        }
        
    }
    
    //���j���[�I��
    public static void managerMenu(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
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
                    addStaff(userID, password);
                    break;
                case 2:
                    deleteStaff(userID, password);
                    break;
                case 3:
                    updateStaff(userID, password);
                    break;
                case 4:
                    System.out.println("�]�ƈ��ꗗ");
                    company.employeeView(userID, password);
                    break;
                case 5:
                    company.informationView(userID, password);
                    break;
                case 6:
                    company.assignmentView(userID, password);
                    break;
                case 7:
                    searchStaffs(userID, password);
                    break;
                case 8:
                exid = 1; 
                    break;
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //���j���[�I��
    public static void userMenu(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
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
                    company.employeeView(userID, password);
                    break;
                case 2:
                    company.informationView(userID, password);
                    break;
                case 3:
                    company.assignmentView(userID, password);
                    break;
                case 4:
                    searchStaffs(userID, password);
                    break;
                case 5:
                exid = 1; 
                    break;
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }

    //�]�ƈ��̓o�^
    public static void addStaff(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        Staff data = new Staff();
        data.setEmp_id(addID());
        data.setEmp_name(addName());
        data.setEmp_gender(addGender());
        data.setBirth(addBirth());
        data.setPosition(addPosition());
        data.setAssignment(addAssignment());
        data.setYearsWorked(addYearsWorked());
        data.setCertificate(addCertificate());
        data.setRewardsPunishments(addRewardsPunishments());
        data.setProgrammingLanguage(addProgrammingLanguage());
        company.addStaff(data, userID, password);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //�]�ƈ�ID�̓���
    public static int addID(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("�]�ƈ�ID����͂��Ă�������");
        int emp_id = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_id;
    }
    
    //�]�ƈ����̓���
    public static String addName(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("�]�ƈ�������͂��Ă�������");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_name;
    }
    
    //���ʂ̓���
    public static char addGender(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("���ʂ�M�i�j���j�AF�i�����j�AX�i���̑��j����I�������͂��Ă�������");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_gender;
    }
    
    //���N�����̓���
    public static Date addBirth(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
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
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return Birth;
    }
    
    //��E�̓���
    public static String addPosition(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("��E����͂��Ă�������");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return position;
    }
    
    //�����̓���
    public static String addAssignment(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("��������͂��Ă�������");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return assignment;
    }
    
    //�Ζ��N���̓���
    public static int addYearsWorked(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("�Ζ��N������͂��Ă�������");
        int yearsWorked = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return yearsWorked;
    }
    
    //���i�̓���
    public static List<String> addCertificate(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int i=0;
        List<String> certificate = new ArrayList<String>();
        for(int j = 0 ; j < 20 ; j++){
            System.out.println("���i����͂��Ă�������");
            String strCertificate = new java.util.Scanner(System.in).nextLine();
            certificate.add(strCertificate);
            System.out.println("�I������ꍇ��1�������Ă��������B");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 20;
            }
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return certificate;
    }
    
    //�ܔ��̓���
    public static List<String> addRewardsPunishments(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int i=0;
        List<String> rewardsPunishments = new ArrayList<String>();
        for(int j = 0 ; j < 20 ; j++){
            System.out.println("�ܔ�����͂��Ă�������");
            String strRewardsPunishments = new java.util.Scanner(System.in).nextLine();
            rewardsPunishments.add(strRewardsPunishments);
            System.out.println("�I������ꍇ��1�������Ă��������B");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 20;
            }
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return rewardsPunishments;
    }
    
    //�g�p����̓���
    public static String addProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("�g�p�������͂��Ă�������");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    //�]�ƈ��̍폜
    public static void deleteStaff(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("�폜����]�ƈ���ID����͂��Ă�������");
        int delete = new java.util.Scanner(System.in).nextInt();
        company.deleteStaff(delete, userID, password);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //�]�ƈ��̍X�V
    public static void updateStaff(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        boolean exist = false;
        System.out.println("�ύX����]�ƈ���ID����͂��Ă�������");
        int updateID = new java.util.Scanner(System.in).nextInt();
        Staff data = new Staff();
        data.setEmp_id(addID());
        data.setEmp_name(addName());
        data.setEmp_gender(addGender());
        data.setBirth(addBirth());
        data.setPosition(addPosition());
        data.setAssignment(addAssignment());
        data.setYearsWorked(addYearsWorked());
        data.setCertificate(addCertificate());
        data.setRewardsPunishments(addRewardsPunishments());
        data.setProgrammingLanguage(addProgrammingLanguage());
        company.updateStaff(updateID, data, userID, password);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }

    //�]�ƈ��̌���
    public static void searchStaffs(String userID, String password){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("��������]�ƈ��̍��ڂ���͂��Ă��������B");
        System.out.println("1:�Ј�ID\n2:���O\n3:����\n4:���N����\n5:��E\n6:����\n7:�Ζ��N��\n8:���i\n9:�ܔ�\n10:�g�p����i�v���O���~���O�j");
        int selectedInformation = new java.util.Scanner(System.in).nextInt();
        switch(selectedInformation){
            case 1:
                System.out.println("�Ј�ID����͂��Ă��������B");
                int emp_id = new java.util.Scanner(System.in).nextInt();
                company.searchByEmp_id(emp_id, userID, password);
                break;
            case 2:
                System.out.println("�Ј��̖��O����͂��Ă��������B");
                String emp_name = new java.util.Scanner(System.in).nextLine();
                company.searchByEmp_name(emp_name, userID, password);
                break;
            case 3:
                System.out.println("���ʂ���͂��Ă��������B");
                String str = new java.util.Scanner(System.in).nextLine();
                char emp_gender = str.charAt(0);
                company.searchByEmp_gender(emp_gender, userID, password);
                break;
            case 4:
                System.out.println("���N��������͂��Ă��������B��1993-08-19");
                String birth = new java.util.Scanner(System.in).nextLine();
                company.searchByBirth(birth, userID, password);
                break;
            case 5:
                System.out.println("��E����͂��Ă��������B");
                String position = new java.util.Scanner(System.in).nextLine();
                company.searchByPosition(position, userID, password);
                break;
            case 6:
                System.out.println("�������͂��Ă��������B");
                String assignment = new java.util.Scanner(System.in).nextLine();
                company.searchByAssignment(assignment, userID, password);
                break;
            case 7:
                System.out.println("�Ζ��N������͂��Ă��������B");
                int yearsWorked = new java.util.Scanner(System.in).nextInt();
                company.searchByYearsWorked(yearsWorked, userID, password);
                break;
            case 8:
                System.out.println("���i����͂��Ă��������B");
                String certificate = new java.util.Scanner(System.in).nextLine();
                company.searchByCertificate(certificate, userID, password);
                break;
            case 9:
                System.out.println("�ܔ�����͂��Ă��������B");
                String rewardsPunishments = new java.util.Scanner(System.in).nextLine();
                company.searchByRewardsPunishments(rewardsPunishments, userID, password);
                break;
            case 10:
                System.out.println("�g�p����i�v���O���~���O�j����͂��Ă��������B");
                String programmingLanguage = new java.util.Scanner(System.in).nextLine();
                company.searchByProgrammingLanguage(programmingLanguage, userID, password);
                break;
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
}