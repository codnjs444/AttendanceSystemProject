package mix;

import java.util.List;

public class Query {

    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;

    public static final String SELECT_CLASS = "SELECT * FROM class";
    public static final String SELECT_CLASS_NAMEUP = "SELECT * FROM class ORDER BY class_name";
    public static final String SELECT_TABLE_NAME = "SELECT * FROM table_name";

    public static final String SELECT_JAVA_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'java??'";
    
    public static final String SELECT_CAD1_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad1??'";
    
    public static final String SELECT_CAD2_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad2??'";
    
    public static final String SELECT_COMPUTER_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = '컴활??'";
    
    
    
    
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
}
