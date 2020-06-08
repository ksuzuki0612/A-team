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
        // ログレベルの設定
        logger.setLevel(Level.FINER);
        
        System.out.println("従業員IDを入力してください");
        String userID = new java.util.Scanner(System.in).nextLine();
        System.out.println("パスワードを入力してください");
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
    
    //メニュー選択
    public static void managerMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
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
                    addStaff(stmt);
                    break;
                case 2:
                    deleteStaff(stmt);
                    break;
                case 3:
                    updateStaff(stmt);
                    break;
                case 4:
                    System.out.println("従業員一覧");
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
    
    //メニュー選択
    public static void userMenu(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
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

    //従業員の登録
    public static void addStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.addStaff(data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //従業員IDの入力
    public static int inputID(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("従業員IDを入力してください");
        int emp_id = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_id;
    }
    
    //従業員名の入力
    public static String inputName(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("従業員名を入力してください");
        String emp_name = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_name;
    }
    
    //性別の入力
    public static char inputGender(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("性別をM（男性）、F（女性）、X（その他）から選択し入力してください");
        String strGender = new java.util.Scanner(System.in).nextLine();
        char emp_gender = strGender.charAt(0);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return emp_gender;
    }
    
    //生年月日の入力
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
    
    //役職の入力
    public static String inputPosition(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("役職を入力してください");
        String position = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return position;
    }
    
    //所属の入力
    public static String inputAssignment(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("所属を入力してください");
        String assignment = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return assignment;
    }
    
    //勤務年数の入力
    public static int inputYearsWorked(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("勤務年数を入力してください");
        int yearsWorked = new java.util.Scanner(System.in).nextInt();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return yearsWorked;
    }
    
    //資格の入力
    public static List<String> inputCertificate(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int i=0;
        List<String> certificate = new ArrayList<String>();
        for(int j = 0 ; j < 3 ; j++){
            System.out.println("資格を入力してください");
            String strCertificate = new java.util.Scanner(System.in).nextLine();
            certificate.add(strCertificate);
            System.out.println("終了する場合は1を押してください。");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 3;
            }
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return certificate;
    }
    
    //賞罰の入力
    public static List<String> inputAwardsPunishments(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        int i=0;
        List<String> awardsPunishments = new ArrayList<String>();
        for(int j = 0 ; j < 3 ; j++){
            System.out.println("賞罰を入力してください");
            String strAwardsPunishments = new java.util.Scanner(System.in).nextLine();
            awardsPunishments.add(strAwardsPunishments);
            System.out.println("終了する場合は1を押してください。");
            String str = new java.util.Scanner(System.in).nextLine();
            if(str.equals("1")){
                j = 3;
            }
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return awardsPunishments;
    }
    
    //使用言語の入力
    public static String inputProgrammingLanguage(){
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("使用言語を入力してください");
        String programmingLanguage = new java.util.Scanner(System.in).nextLine();
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
        return programmingLanguage;
    }
    
    //従業員の削除
    public static void deleteStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("削除する従業員のIDを入力してください");
        int delete = new java.util.Scanner(System.in).nextInt();
        company.deleteStaff(delete,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
    //従業員の更新
    public static void updateStaff(Statement stmt) throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        boolean exist = false;
        System.out.println("変更する従業員のIDを入力してください");
        int updateID = new java.util.Scanner(System.in).nextInt();
        Staff data = new Staff(inputID(),inputName(),inputGender(),inputBirth(),inputPosition(),inputAssignment(),
            inputYearsWorked(),inputCertificate(),inputAwardsPunishments(),inputProgrammingLanguage());
        company.updateStaff(updateID,data,stmt);
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }

    //従業員の検索
    public static void searchStaffs(Statement stmt)throws Exception{
		logger.entering(LogUtil.getClassName(),LogUtil.getMethodName());
        System.out.println("検索する従業員の項目を入力してください。");
        System.out.println("1:社員ID\n2:名前\n3:性別\n4:生年月日\n5:役職\n6:所属\n7:勤務年数\n8:資格\n9:賞罰\n10:プログラミング言語");
        int selectedInformation = new java.util.Scanner(System.in).nextInt();
        switch(selectedInformation){
            case 1:
                System.out.println("社員IDを入力してください。");
                int emp_id = new java.util.Scanner(System.in).nextInt();
                company.searchByEmp_id(emp_id,stmt);
                break;
            case 2:
                System.out.println("社員の名前を入力してください。");
                String emp_name = new java.util.Scanner(System.in).nextLine();
                company.searchByEmp_name(emp_name,stmt);
                break;
            case 3:
                System.out.println("性別を入力してください。");
                String str = new java.util.Scanner(System.in).nextLine();
                char emp_gender = str.charAt(0);
                company.searchByEmp_gender(emp_gender,stmt);
                break;
            case 4:
                System.out.println("生年月日を入力してください。例1993-08-19");
                String birth = new java.util.Scanner(System.in).nextLine();
                company.searchByBirth(birth,stmt);
                break;
            case 5:
                System.out.println("役職を入力してください。");
                String position = new java.util.Scanner(System.in).nextLine();
                company.searchByPosition(position,stmt);
                break;
            case 6:
                System.out.println("所属入力してください。");
                String assignment = new java.util.Scanner(System.in).nextLine();
                company.searchByAssignment(assignment,stmt);
                break;
            case 7:
                System.out.println("勤務年数を入力してください。");
                int yearsWorked = new java.util.Scanner(System.in).nextInt();
                company.searchByYearsWorked(yearsWorked,stmt);
                break;
            case 8:
                System.out.println("資格を入力してください。");
                String certificate = new java.util.Scanner(System.in).nextLine();
                company.searchByCertificate(certificate,stmt);
                break;
            case 9:
                System.out.println("賞罰を入力してください。");
                String awardsPunishments = new java.util.Scanner(System.in).nextLine();
                company.searchByAwardsPunishments(awardsPunishments,stmt);
                break;
            case 10:
                System.out.println("プログラミング言語を入力してください。");
                String programmingLanguage = new java.util.Scanner(System.in).nextLine();
                company.searchByProgrammingLanguage(programmingLanguage,stmt);
                break;
        }
		logger.exiting(LogUtil.getClassName(),LogUtil.getMethodName());
    }
    
}