package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import Announcement.*;

public class Class_Name {
    public static final List<String> CLASS_NAMES;

    static {
        List<String> classNames = new ArrayList<>();
        try {
            // 데이터베이스 연결
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem", "root", "1234");

            // 쿼리 실행
            Statement stmt = conn.createStatement();
            String query1 = Query.SELECT_CLASS_NAMEUP;
            ResultSet rs = stmt.executeQuery(query1);

            // 결과 처리
            while (rs.next()) {
                classNames.add(rs.getString("class_name"));
            }
            // 리소스 닫기
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        CLASS_NAMES = Collections.unmodifiableList(classNames);
    }

    public static void main(String[] args) {
        // 버튼 생성 및 이벤트 처리는 Top_MenuBar 클래스에서 수행
    	Announcement_admin mainmenu = new Announcement_admin();
    }
}
