package config;
import java.util.List;

public class Query {
	
	private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
	
    // 공지사항 관련 쿼리////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String SELECT_CLASS = "SELECT * FROM class";
    public static final String SELECT_CLASS_NAMEUP = "SELECT * FROM class ORDER BY class_name";
    public static final String SELECT_TABLE_NAME = "SELECT * FROM table_name";
    
    public static final String SELECT_JAVA_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'java반'";
    
    public static final String SELECT_CAD1_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad1급반'";
    
    public static final String SELECT_CAD2_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad2급반'";
    
    public static final String SELECT_COMPUTER_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = '컴활반'";
    // 출결 관련 쿼리////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String Select_ADMIN_ID = "SELECT user_name FROM user WHERE user_id IN (SELECT user_id FROM user WHERE user_idvalue = 1)";
    public static final String FIND_USER_ID = "SELECT user_id FROM user WHERE user_name = ?";
    public static final String INSERT_ANNOUNCEMENT = "INSERT INTO announcement (announcement_title, announcement_content, announcement_writer, announcement_class, announcement_date, user_id) VALUES (?, ?, ?, ?, ?, ?)";
    public static final String DELETE_ANNOUNCEMENT = "DELETE FROM announcement WHERE announcement_num = ?";
    
    public static final String UPDATE_ATTENDANCE = "UPDATE attendance SET attendance_status = ? WHERE user_id = ?";

    public static final String SELECT_ATTENDANCE = "SELECT u.user_name, u.user_major, u.user_id, a.attendance_in, a.attendance_out, a.attendance_status " +
            "FROM user u " +
            "JOIN attendance a ON u.user_id = a.user_id " +
            "WHERE u.class_name = ? AND a.attendance_date = ?";
    
    public static final String SELECT_STU_ATTENDANCE = "SELECT u.user_name, u.user_major, a.attendance_in, a.attendance_out, a.attendance_status " +
    	    "FROM attendance a " +
    	    "JOIN user u ON a.user_id = u.user_id " +
    	    "WHERE a.user_id = ?";
    
    
    //Profile/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static final String SELECT_CAD1STU_TABLE = "SELECT user_id, user_major, user_name, user_gender " +
            "FROM user " + 
            "WHERE class_name = 'cad1급반' AND user_idValue = 0";
      
      public static final String SELECT_CAD2STU_TABLE = "SELECT user_id, user_major, user_name, user_gender " +
            "FROM user " + 
            "WHERE class_name = 'cad2급반' AND user_idValue = 0";
      
      public static final String SELECT_JAVASTU_TABLE = "SELECT user_id, user_major, user_name, user_gender " +
            "FROM user " + 
            "WHERE class_name = 'java반' AND user_idValue = 0";
      
      public static final String SELECT_COMSTU_TABLE = "SELECT user_id, user_major, user_name, user_gender " +
            "FROM user "+
            "WHERE class_name = '컴활반' AND user_idValue = 0";
      
//연수////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
      
      public static final String SELECT_CLASS_NAMES = "SELECT class_name FROM class";
  	
  	//유저 일일 출석 통계 쿼리(join 포함)
  	public static final String SELECT_USER_ATTENDANCE = "SELECT * FROM attendance WHERE user_id = ?";   
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
      

  	//유저 일일 출석 통계 쿼리(join 포함)
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

  	//유저 일일 출석 통계 쿼리(join 포함)
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

  	//유저 일일 출석 통계 쿼리(join 포함)
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

  	//유저 월 출석 통계 쿼리(join 포함)
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

  	//유저 총 출석 통계 쿼리(join 포함)
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
      
   // Query to fetch overall attendance counts for a user
      public static final String FETCH_OVERALL_ATTENDANCE_DATA = "SELECT SUM(CASE WHEN attendance_status = '출석' THEN 1 ELSE 0 END) as attendance_count, " +
              "SUM(CASE WHEN attendance_status = '결석' THEN 1 ELSE 0 END) as absence_count, " +
              "SUM(CASE WHEN attendance_status = '지각' THEN 1 ELSE 0 END) as tardiness_count " +
              "FROM attendance WHERE user_id = ?";

      public static final String FETCH_THIS_MONTH_ATTENDANCE_DATA = "SELECT SUM(CASE WHEN attendance_status = '출석' THEN 1 ELSE 0 END) as attendance_count, " +
              "SUM(CASE WHEN attendance_status = '결석' THEN 1 ELSE 0 END) as absence_count, " +
              "SUM(CASE WHEN attendance_status = '지각' THEN 1 ELSE 0 END) as tardiness_count " +
              "FROM attendance WHERE user_id = ? AND attendance_date >= ? AND attendance_date <= ?";
 }
      
      

