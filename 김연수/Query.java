package attendancesystema;
import java.util.List;

public class Query {
    
//    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
//    
//    public static final String SELECT_CLASS = "SELECT * FROM class";
//    public static final String SELECT_CLASS_NAMEUP = "SELECT * FROM class ORDER BY class_name";
//    public static final String SELECT_TABLE_NAME = "SELECT * FROM table_name";
//    
//    public static final String SELECT_JAVA_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
//            "FROM announcement " +
//            "WHERE announcement_class = 'java諛�'";
//    
//    public static final String SELECT_CAD1_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
//            "FROM announcement " +
//            "WHERE announcement_class = 'cad1諛�'";
//    
//    public static final String SELECT_CAD2_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
//            "FROM announcement " +
//            "WHERE announcement_class = 'cad2諛�'";
//    
//    public static final String SELECT_COMPUTER_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
//            "FROM announcement " +
//            "WHERE announcement_class = '而댄솢諛�'";
//    
    
    
   
    
  
	public static final String SELECT_CLASS_NAMES = "SELECT class_name FROM class";
    public static final String GET_ATTENDANCE_STATISTICS_BY_JAVA =
            "SELECT " +
            "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
            "FROM " +
            "    user " +
            "    INNER JOIN class ON user.class_num = class.class_num " +
            "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +
            "WHERE " +
            "    class.class_name = ?" +
    "    AND DATE(attendance_date) = CURDATE()";
    
    public static final String GET_ATTENDANCE_STATISTICS_BY_CAD1 = 
            "SELECT " +
            "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
            "FROM " +
            "    user " +
            "    INNER JOIN class ON user.class_num = class.class_num " +
            "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +
            "WHERE " +
            "    class.class_name = ?"+
            "    AND DATE(attendance_date) = CURDATE()";
    
    public static final String GET_ATTENDANCE_STATISTICS_BY_CAD2 = 
            "SELECT " +
            "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
            "FROM " +
            "    user " +
            "    INNER JOIN class ON user.class_num = class.class_num " +
            "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +
            "WHERE " +
            "    class.class_name = ?"+
            "    AND DATE(attendance_date) = CURDATE()";
    
    public static final String GET_ATTENDANCE_STATISTICS_BY_COM = 
            "SELECT " +
            "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
            "FROM " +
            "    user " +
            "    INNER JOIN class ON user.class_num = class.class_num " +
            "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +
            "WHERE " +
            "    class.class_name = ?"+
            "    AND DATE(attendance_date) = CURDATE()";
    
    public static final String GET_ATTENDANCE_STATISTICS_BY_MONTH = 
            "SELECT " +
            "    MONTH(attendance_date) AS month, " +
            "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
            "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
            "FROM " +
            "    user " +
            "    INNER JOIN class ON user.class_num = class.class_num " +
            "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +	
            "WHERE " +
            "    class.class_name = ?" +
            "    AND YEAR(attendance_date) = YEAR(CURDATE()) " +
            "GROUP BY " +
            "    MONTH(attendance_date)";
    
    public static final String GET_ATTENDANCE_STATISTICS_BY_all =
    	    "SELECT " +
    	    "    SUM(CASE WHEN attendance.attendance_status = '출석' THEN 1 ELSE 0 END) AS present_count, " +
    	    "    SUM(CASE WHEN attendance.attendance_status = '결석' THEN 1 ELSE 0 END) AS absent_count, " +
    	    "    SUM(CASE WHEN attendance.attendance_status = '지각' THEN 1 ELSE 0 END) AS tardy_count " +
    	    "FROM " +
    	    "    user " +
    	    "    INNER JOIN class ON user.class_num = class.class_num " +
    	    "    LEFT JOIN attendance ON user.user_id = attendance.user_id " +
    	    "WHERE " +
    	    "    class.class_name = ?" +
    	    "    AND attendance.attendance_date IS NOT NULL";
    
}
