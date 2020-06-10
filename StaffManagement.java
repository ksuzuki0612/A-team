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
        // ログレベルの設定
        logger.setLevel(Level.FINER);
        String logout = "";
        do{
            try {
                System.out.println("従業員IDを入力してください");
                int userID = new java.util.Scanner(System.in).nextInt();
                String id = "" + userID;
                System.out.println("パスワードを入力してください");
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
                System.out.println("認証エラーが発生しました。\nユーザー名またはパスワードが不正です。");
            }
            System.out.println("終了する場合は1を入力してください");
            logout = new java.util.Scanner(System.in).nextLine();
        }while(!logout.equals("1"));
    }
    
    /**
    * 管理者メニュー
    */
    public static void managerMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[メニュー]");
            System.out.println("1:編集メニュー");
            System.out.println("2.一覧表示メニュー");
            System.out.println("3.検索メニュー");
            System.out.println("4.戻る");
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
            	System.out.println("選択されたメニューが見つかりません1");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    
    /**
    * 一般のメニュー
    */
    public static void userMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[メニュー]");
            System.out.println("1.一覧表示メニュー");
            System.out.println("2.検索メニュー");
            System.out.println("3.戻る");
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
            	System.out.println("選択されたメニューが見つかりません2");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * 編集メニュー
    */
    public static void aditStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            System.out.println("\n[メニュー]");
            System.out.println("1:従業員登録");
            System.out.println("2.従業員削除");
            System.out.println("3.従業員更新");
            System.out.println("4.戻る\n");
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
            	System.out.println("選択されたメニューが見つかりません3");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * 従業員の登録
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
    * 従業員IDの入力
    */
    public static int inputID(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean dateCheck = false;
        int id = 0;
        do {
            System.out.println("従業員IDを入力してください");
            String emp_id = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                id = Integer.parseInt(emp_id);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("正しく入力してください");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return id;
    }
    
    /**
    * 従業員名の入力
    */
    public static String inputName(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("従業員名を入力してください");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_name;
    }
    
    /**
    * 性別の入力
    */
    
    public static char inputGender(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("性別をM（男性）、F（女性）、X（その他）から選択し入力してください");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_gender;
    }
    
    /**
    * 生年月日の入力
    */
    public static Date inputBirth(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
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
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return Birth;
    }
    
    /**
    * 役職の入力
    */
    public static String inputPosition(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("役職を入力してください");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return position;
    }
    
    /**
    * 所属の入力
    */
    public static String inputAssignment(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("所属を入力してください");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return assignment;
    }
    
    /**
    * 勤務年数の入力
    */
    public static int inputYearsWorked(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean dateCheck = false;
        int year = 0;
        do {
            System.out.println("勤務年数を入力してください");
            String yearsWorked = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                year = Integer.parseInt(yearsWorked);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("正しく入力してください");
            }
        }while(dateCheck == false);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return year;
    }
    
    /**
    * 資格の入力
    */
    public static String inputCertificate(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("資格を入力してください");
        String strCertificate = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return strCertificate;
    }
    
    /**
    * 賞罰の入力
    */
    public static String inputAwardsPunishments(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("賞罰を入力してください");
        String strAwardsPunishments = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return strAwardsPunishments;
    }
    
    /**
    * 使用言語の入力
    */
    public static String inputProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("使用言語を入力してください");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    /**
    * 従業員の削除
    */
    public static void deleteStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("削除する従業員のIDを入力してください");
        String delete = new java.util.Scanner(System.in).nextLine();
        company.deleteStaff(delete,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * 従業員の更新
    */
    public static void updateStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean exist = false;
        boolean dateCheck = false;
        int updateID = 0;
        do {
            System.out.println("変更する従業員のIDを入力してください");
            String id = new java.util.Scanner(System.in).nextLine();
            try(FileWriter fw = new FileWriter("data.txt");){
                updateID = Integer.parseInt(id);
                dateCheck = true;
            }catch(Exception e) {
                System.out.println("正しく入力してください");
            }
        }while(dateCheck == false);
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputCertificate(),inputCertificate(),inputAwardsPunishments(),
            inputAwardsPunishments(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.updateStaff(updateID,data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * 一覧表示メニュー
    */
    public static void viewStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            boolean exit = false;
            System.out.println("\n[メニュー]");
            System.out.println("1:従業員一覧");
            System.out.println("2.個人情報一覧");
            System.out.println("3.アサインメント状況一覧");
            System.out.println("4.戻る\n");
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
            	System.out.println("選択されたメニューが見つかりません4");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    /**
    * 従業員の検索
    */
    public static void searchStaffs(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int exid = 0;
        do {
            boolean exit = false;
            System.out.println("\n検索する従業員の項目を入力してください。");
            System.out.println("1:社員ID\n2:名前\n3:性別\n4:生年月日\n5:役職\n6:所属\n7:勤務年数\n8:資格\n9:賞罰\n10:プログラミング言語\n11:戻る\n");
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
                System.out.println("選択された項目がありません");
            }
        }while(exid != 1);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
}
