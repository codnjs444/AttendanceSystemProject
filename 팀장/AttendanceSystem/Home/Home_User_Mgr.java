package Home;
import config.*;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Home_User_Mgr {
    private Connection connection;
    private String loggedInUserID;

    public Home_User_Mgr(Connection connection, String loggedInUserID) {
        this.connection = connection;
        this.loggedInUserID = loggedInUserID;
    }

   
    public JPanel createOverallAttendancePanel() {
        JPanel overallAttendancePanel = new JPanel();
        overallAttendancePanel.setBackground(Color.WHITE);
        overallAttendancePanel.setLayout(new BorderLayout());
        overallAttendancePanel.setPreferredSize(new Dimension(200, 150));

       
        JLabel overallAttendanceLabel = new JLabel("나의 출결 상황 (전체)");
        overallAttendanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overallAttendanceLabel.setFont(new Font("Inter", Font.BOLD, 16));
        overallAttendancePanel.add(overallAttendanceLabel, BorderLayout.NORTH);

       
        String overallAttendanceData = fetchOverallAttendanceData(loggedInUserID);

        //JLabel overallAttendanceDataLabel = new JLabel(overallAttendanceData);
        JLabel overallAttendanceDataLabel = createTransparentLabel(fetchOverallAttendanceData(loggedInUserID));
        overallAttendanceDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        overallAttendancePanel.add(overallAttendanceDataLabel, BorderLayout.CENTER);

        return overallAttendancePanel;
    }
    private JLabel createTransparentLabel(String text) {
        JLabel transparentLabel = new JLabel(text) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setColor(new Color(255, 255, 255, 100)); // Transparent white color
                g2d.fillRect(0, 0, getWidth(), getHeight()); // Fill the entire label with a transparent rectangle
                g2d.setColor(new Color(133, 91, 221));
                g2d.drawRect(160, 25, getWidth() - 300, getHeight() - 50); // Draw a black border around the label
                g2d.dispose();
            }
        };
        transparentLabel.setOpaque(false); // Make the label transparent
        return transparentLabel;
    }

    
    public JPanel createThisMonthAttendancePanel() {
        JPanel thisMonthAttendancePanel = new JPanel();
        thisMonthAttendancePanel.setBackground(Color.WHITE);
        thisMonthAttendancePanel.setLayout(new BorderLayout());
        thisMonthAttendancePanel.setPreferredSize(new Dimension(180, 90));

     
        JLabel thisMonthAttendanceLabel = new JLabel("나의 출결 상황 (이번 달)");
        thisMonthAttendanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thisMonthAttendanceLabel.setFont(new Font("Inter", Font.BOLD, 16));
        thisMonthAttendancePanel.add(thisMonthAttendanceLabel, BorderLayout.NORTH);

        
        String thisMonthAttendanceData = fetchThisMonthAttendanceData(loggedInUserID);

        JLabel thisMonthAttendanceDataLabel = createTransparentLabel(fetchOverallAttendanceData(loggedInUserID));
        
        thisMonthAttendanceDataLabel.setHorizontalAlignment(SwingConstants.CENTER);
        thisMonthAttendancePanel.add(thisMonthAttendanceDataLabel, BorderLayout.CENTER);

        return thisMonthAttendancePanel;
    }
    
    private String fetchOverallAttendanceData(String userID) {
        try {
            PreparedStatement pstmt = connection.prepareStatement(Query.FETCH_OVERALL_ATTENDANCE_DATA);
            pstmt.setString(1, userID);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int attendanceCount = rs.getInt("attendance_count");
                int absenceCount = rs.getInt("absence_count");
                int tardinessCount = rs.getInt("tardiness_count");

                
                String attendanceData = String.format("<html><head><style>body { font-size: 12px; }</style></head>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;출석&nbsp;&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp결석&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;지각&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d</html>",
                        attendanceCount, absenceCount, tardinessCount);

                return attendanceData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "에러임";
    }


    
    private String fetchThisMonthAttendanceData(String userID) {
        try {
            LocalDate now = LocalDate.now();
            LocalDate firstDayOfMonth = now.withDayOfMonth(1);
            LocalDate lastDayOfMonth = now.withDayOfMonth(now.lengthOfMonth());

            PreparedStatement pstmt = connection.prepareStatement(Query.FETCH_THIS_MONTH_ATTENDANCE_DATA);
            pstmt.setString(1, userID);
            pstmt.setDate(2, java.sql.Date.valueOf(firstDayOfMonth));
            pstmt.setDate(3, java.sql.Date.valueOf(lastDayOfMonth));
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int attendanceCount = rs.getInt("attendance_count");
                int absenceCount = rs.getInt("absence_count");
                int tardinessCount = rs.getInt("tardiness_count");

                
                String attendanceData = String.format("<html>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;출석&nbsp;&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp결석&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp;지각&nbsp;<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;%d</html>",
                        attendanceCount, absenceCount, tardinessCount);

                return attendanceData;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "에러임";
    }
}
        