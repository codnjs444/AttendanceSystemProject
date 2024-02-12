package attendancesystem;

import java.util.List;

public class Query {
	
	private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
	
    public static final String SELECT_CLASS = "SELECT * FROM class";
    public static final String SELECT_CLASS_NAMEUP = "SELECT * FROM class ORDER BY class_name";
    public static final String SELECT_TABLE_NAME = "SELECT * FROM table_name";
    
    public static final String SELECT_JAVA_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'java반'";
    
    public static final String SELECT_CAD1_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad1반'";
    
    public static final String SELECT_CAD2_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = 'cad2반'";
    
    public static final String SELECT_COMPUTER_TABLE = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
            "FROM announcement " +
            "WHERE announcement_class = '컴활반'";
}
