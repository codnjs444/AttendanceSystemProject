package Attendance;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import config.MysqlConnection;
import config.Query;

public class AttendanceMgr {
    public static void nowData(String className, JTable table) {
        try {
            // 현재 날짜 가져오기
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = sdf.format(new Date());

            // 데이터베이스에서 데이터를 가져오는 쿼리 실행
            String query = Query.SELECT_ATTENDANCE;
            PreparedStatement pstmt = MysqlConnection.getConnection().prepareStatement(query);
            pstmt.setString(1, className);
            pstmt.setString(2, currentDate);
            ResultSet rs = pstmt.executeQuery();

            // 결과를 dataList에 추가하기 전에 dataList을 초기화
            List<Object[]> dataList = new ArrayList<>();

            // 결과를 dataList에 추가
            while (rs.next()) {
                String userName = rs.getString("user_name");
                String major = rs.getString("user_major");
                String userId = rs.getString("user_id");
                String attendanceIn = rs.getString("attendance_in");
                String attendanceOut = rs.getString("attendance_out");
                String attendanceStatus = rs.getString("attendance_status");
                Object[] rowData = {userName, major, userId, attendanceIn, attendanceOut, attendanceStatus};
                dataList.add(rowData);
            }

            rs.close();
            pstmt.close();

            if (dataList.isEmpty()) {
                JOptionPane.showMessageDialog(null, "출결 데이터가 없습니다.");
            }

            // 데이터를 모델에 반영
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setDataVector(dataList.toArray(new Object[0][0]), new Object[]{"이름", "전공", "아이디", "출근시간", "퇴근시간", "출석현황"});
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void selectData(String className, String selectedDate, JTable table) {
        // 데이터를 담을 리스트 초기화
        List<Object[]> dataList = new ArrayList<>();
        try {
            // 데이터베이스에서 데이터를 가져오는 쿼리 실행
            String query = Query.SELECT_ATTENDANCE;
            PreparedStatement pstmt = MysqlConnection.getConnection().prepareStatement(query);
            pstmt.setString(1, className);
            pstmt.setString(2, selectedDate);
            ResultSet rs = pstmt.executeQuery();

            // 결과를 dataList에 추가
            while (rs.next()) {
                String userName = rs.getString("user_name");
                String major = rs.getString("user_major");
                String userId = rs.getString("user_id");
                String attendanceIn = rs.getString("attendance_in");
                String attendanceOut = rs.getString("attendance_out");
                String attendanceStatus = rs.getString("attendance_status");
                Object[] rowData = {userName, major, userId, attendanceIn, attendanceOut, attendanceStatus};
                dataList.add(rowData);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 데이터를 모델에 반영
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setDataVector(dataList.toArray(new Object[0][0]), new Object[]{"이름", "전공", "아이디", "출근시간", "퇴근시간", "출석현황"});
        
        // dataList의 크기가 0인 경우 "출결 정보가 없습니다"를 출력
        if (dataList.isEmpty()) {
            JOptionPane.showMessageDialog(null, "출결 정보가 없습니다.");
        }
    }
}