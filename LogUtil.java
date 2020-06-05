public class LogUtil {
    public static String getMethodName() {
        return Thread.currentThread().getStackTrace()[2].getMethodName();
    }
    public static String getClassName() {
        return Thread.currentThread().getStackTrace()[2].getClassName();
    }
    public static String getThreadName(){
       return Thread.currentThread().getName();
    }
}