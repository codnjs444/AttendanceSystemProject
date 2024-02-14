package Attendance;
import page.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.*;

public class Attendance_view extends JPanel {
    private String studentId;
    private String studentName;
    private String studentMajor;
    private String attendanceIn;
    private String attendanceOut;
    private String attendanceStatus;

    public Attendance_view(String studentId) {
        fetchDataFromDatabase(studentId);
        initComponents();
    }

    private void fetchDataFromDatabase(String studentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = MysqlConnection.getConnection();

            String query = Query.SELECT_STU_ATTENDANCE;
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, studentId);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                studentName = rs.getString("user_name");
                studentMajor = rs.getString("user_major");
                attendanceIn = rs.getString("attendance_in");
                attendanceOut = rs.getString("attendance_out");
                attendanceStatus = rs.getString("attendance_status");
                this.studentId = studentId;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void initComponents() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(450, 280)); // 패널의 크기 설정

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        
        JLabel titleLabel = new JLabel("학생 출결 정보");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24)); // 글꼴 및 크기 설정
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea nameArea = new JTextArea("이름: " + studentName);
        nameArea.setEditable(false);
        nameArea.setLineWrap(true);
        nameArea.setWrapStyleWord(true);
        nameArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea majorArea = new JTextArea("전공: " + studentMajor);
        majorArea.setEditable(false);
        majorArea.setLineWrap(true);
        majorArea.setWrapStyleWord(true);
        majorArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea inTimeArea = new JTextArea("출근 시간: " + attendanceIn);
        inTimeArea.setEditable(false);
        inTimeArea.setLineWrap(true);
        inTimeArea.setWrapStyleWord(true);
        inTimeArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea outTimeArea = new JTextArea("퇴근 시간: " + attendanceOut);
        outTimeArea.setEditable(false);
        outTimeArea.setLineWrap(true);
        outTimeArea.setWrapStyleWord(true);
        outTimeArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea statusArea = new JTextArea("출석 상태: " + attendanceStatus);
        statusArea.setEditable(false);
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true);
        statusArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JPanel infoPanel = new JPanel(new GridLayout(5, 1));
        infoPanel.add(nameArea);
        infoPanel.add(majorArea);
        infoPanel.add(inTimeArea);
        infoPanel.add(outTimeArea);
        infoPanel.add(statusArea);
        panel.add(infoPanel, BorderLayout.CENTER);

        // 출석 상태를 선택할 수 있는 콤보 박스 생성
        String[] statusOptions = {"출석", "지각", "결석"};
        JComboBox<String> statusComboBox = new JComboBox<>(statusOptions);
        statusComboBox.setSelectedItem(attendanceStatus); // 현재 출석 상태로 설정

        // 저장 버튼 생성
        JButton saveButton = new JButton("저장");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 콤보 박스에서 선택된 출석 상태 가져오기
                String newStatus = (String) statusComboBox.getSelectedItem();
                // 데이터베이스에 출석 상태 업데이트
                updateAttendanceStatus(studentId, newStatus);
                // 부모 프레임 닫기
                SwingUtilities.getWindowAncestor(Attendance_view.this).dispose();
            }
        });

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(statusComboBox);
        buttonPanel.add(saveButton);

        // 기존 패널에 버튼 패널 추가
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
    }

    // 출석 상태를 데이터베이스에 업데이트하는 메서드
    private void updateAttendanceStatus(String studentId, String newStatus) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = MysqlConnection.getConnection();
            String query = Query.UPDATE_ATTENDANCE;
            
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newStatus);
            pstmt.setString(2, studentId);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "출석 상태가 업데이트되었습니다.");
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Attendance View");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Attendance_view("student123")); // 학생 ID를 인자로 전달
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}