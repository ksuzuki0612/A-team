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
        // ログレベルの設定
        logger.setLevel(Level.FINER);
        
        System.out.println("従業員IDを入力してください");
        String userID = new java.util.Scanner(System.in).nextLine();
        System.out.println("パスワードを入力してください");
        String password = new java.util.Scanner(System.in).nextLine();
        if(company.Login(userID, password) == true){
            if(company.Authority(userID, password) == true){
                managerMenu(userID, password);
            }else{
            	userMenu(userID, password);
            }
        }
        
    }
    
    //メニュー選択
    public static void managerMenu(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[メニュー]");
            System.out.println("1:従業員登録");
            System.out.println("2.従業員削除");
            System.out.println("3.従業員更新");
            System.out.println("4.従業員一覧");
            System.out.println("5.個人情報一覧");
            System.out.println("6.アサインメント状況一覧");
            System.out.println("7.検索");
            System.out.println("8.終了\n");
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
                    System.out.println("従業員一覧");
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
    
    //メニュー選択
    public static void userMenu(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[メニュー]");
            System.out.println("1:従業員一覧");
            System.out.println("2.個人情報一覧");
            System.out.println("3.アサインメント状況一覧");
            System.out.println("4.検索");
            System.out.println("5.終了");
            int number = new java.util.Scanner(System.in).nextInt();
            switch (number) {
                case 1:
                    System.out.println("従業員一覧");
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

    //従業員の登録
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
    
    //従業員IDの入力
    public static int addID(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("従業員IDを入力してください");
        int emp_id = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_id;
    }
    
    //従業員名の入力
    public static String addName(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("従業員名を入力してください");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_name;
    }
    
    //性別の入力
    public static char addGender(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("性別をM（男性）、F（女性）、X（その他）から選択し入力してください");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return emp_gender;
    }
    
    //生年月日の入力
    public static Date addBirth(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        boolean dateCheck = false;
        Date Birth = new Date(1600705425827L);
        do {
            System.out.println("生年月日を入力してください");
            String strBirth = new java.util.Scanner(System.in).nextLine();
            SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy-mm-dd");
            try(FileWriter fw = new FileWriter("data.txt");){
                Date date = sdFormat.parse(strBirth);
                Birth = date;
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("正しく入力してください");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return Birth;
    }
    
    //役職の入力
    public static String addPosition(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("役職を入力してください");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return position;
    }
    
    //所属の入力
    public static String addAssignment(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("所属を入力してください");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return assignment;
    }
    
    //勤務年数の入力
    public static int addYearsWorked(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("勤務年数を入力してください");
        int yearsWorked = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return yearsWorked;
    }
    
    //資格の入力
    public static List<String> addCertificate(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int i=0;
        List<String> certificate = new ArrayList<String>();
        for(int j = 0 ; j < 20 ; j++){
            System.out.println("資格を入力してください");
            String strCertificate = new java.util.Scanner(System.in).nextLine();
            certificate.add(strCertificate);
            System.out.println("終了する場合は1を押してください。");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 20;
            }
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return certificate;
    }
    
    //賞罰の入力
    public static List<String> addRewardsPunishments(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        int i=0;
        List<String> rewardsPunishments = new ArrayList<String>();
        for(int j = 0 ; j < 20 ; j++){
            System.out.println("賞罰を入力してください");
            String strRewardsPunishments = new java.util.Scanner(System.in).nextLine();
            rewardsPunishments.add(strRewardsPunishments);
            System.out.println("終了する場合は1を押してください。");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 20;
            }
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return rewardsPunishments;
    }
    
    //使用言語の入力
    public static String addProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("使用言語を入力してください");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    //従業員の削除
    public static void deleteStaff(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("削除する従業員のIDを入力してください");
        int delete = new java.util.Scanner(System.in).nextInt();
        company.deleteStaff(delete, userID, password);
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
    //従業員の更新
    public static void updateStaff(String userID, String password) {
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        boolean exist = false;
        System.out.println("変更する従業員のIDを入力してください");
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

    //従業員の検索
    public static void searchStaffs(String userID, String password){
		logger.entering(LogUtil.getClassName(), LogUtil.getMethodName());
        System.out.println("検索する従業員の項目を入力してください。");
        System.out.println("1:社員ID\n2:名前\n3:性別\n4:生年月日\n5:役職\n6:所属\n7:勤務年数\n8:資格\n9:賞罰\n10:使用言語（プログラミング）");
        int selectedInformation = new java.util.Scanner(System.in).nextInt();
        switch(selectedInformation){
            case 1:
                System.out.println("社員IDを入力してください。");
                int emp_id = new java.util.Scanner(System.in).nextInt();
                company.searchByEmp_id(emp_id, userID, password);
                break;
            case 2:
                System.out.println("社員の名前を入力してください。");
                String emp_name = new java.util.Scanner(System.in).nextLine();
                company.searchByEmp_name(emp_name, userID, password);
                break;
            case 3:
                System.out.println("性別を入力してください。");
                String str = new java.util.Scanner(System.in).nextLine();
                char emp_gender = str.charAt(0);
                company.searchByEmp_gender(emp_gender, userID, password);
                break;
            case 4:
                System.out.println("生年月日を入力してください。例1993-08-19");
                String birth = new java.util.Scanner(System.in).nextLine();
                company.searchByBirth(birth, userID, password);
                break;
            case 5:
                System.out.println("役職を入力してください。");
                String position = new java.util.Scanner(System.in).nextLine();
                company.searchByPosition(position, userID, password);
                break;
            case 6:
                System.out.println("所属入力してください。");
                String assignment = new java.util.Scanner(System.in).nextLine();
                company.searchByAssignment(assignment, userID, password);
                break;
            case 7:
                System.out.println("勤務年数を入力してください。");
                int yearsWorked = new java.util.Scanner(System.in).nextInt();
                company.searchByYearsWorked(yearsWorked, userID, password);
                break;
            case 8:
                System.out.println("資格を入力してください。");
                String certificate = new java.util.Scanner(System.in).nextLine();
                company.searchByCertificate(certificate, userID, password);
                break;
            case 9:
                System.out.println("賞罰を入力してください。");
                String rewardsPunishments = new java.util.Scanner(System.in).nextLine();
                company.searchByRewardsPunishments(rewardsPunishments, userID, password);
                break;
            case 10:
                System.out.println("使用言語（プログラミング）を入力してください。");
                String programmingLanguage = new java.util.Scanner(System.in).nextLine();
                company.searchByProgrammingLanguage(programmingLanguage, userID, password);
                break;
        }
		logger.exiting(LogUtil.getClassName(), LogUtil.getMethodName());
    }
    
}