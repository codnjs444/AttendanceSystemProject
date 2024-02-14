package mix;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Class_Name {
    public static final List<String> CLASS_NAMES;

    static {
        List<String> classNames = new ArrayList<>();
        try {
            // �����ͺ��̽� ����
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem", "root", "1234");

            // ���� ����
            Statement stmt = conn.createStatement();
            String query1 = Query.SELECT_CLASS_NAMEUP;
            ResultSet rs = stmt.executeQuery(query1);

            // ��� ó��
            while (rs.next()) {
                classNames.add(rs.getString("class_name"));
            }
            // ���ҽ� �ݱ�
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        CLASS_NAMES = Collections.unmodifiableList(classNames);
    }

    public static void main(String[] args) {
        // ��ư ���� �� �̺�Ʈ ó���� Top_MenuBar Ŭ�������� ����
    	Main_Menu mainmenu = new Main_Menu();
    }
}
