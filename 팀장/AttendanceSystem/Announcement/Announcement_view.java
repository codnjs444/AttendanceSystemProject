package Announcement;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import config.*;

public class Announcement_view extends JPanel {
    private String announcementWriter;
    private String announcementClass;
    private String announcementContent;
    private String announcementTitle;

    public Announcement_view(String announcementTitle) {
        fetchDataFromDatabase(announcementTitle);
        initComponents();
    }

    private void fetchDataFromDatabase(String announcementTitle) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = MysqlConnection.getConnection();

            String query = "SELECT announcement_writer, announcement_class, announcement_content FROM announcement WHERE announcement_title = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, announcementTitle);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                announcementWriter = rs.getString("announcement_writer");
                announcementClass = rs.getString("announcement_class");
                announcementContent = rs.getString("announcement_content");
                this.announcementTitle = announcementTitle;
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
        setPreferredSize(new Dimension(600, 400)); // 패널의 크기 설정

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("공지사항 내용");
        titleLabel.setFont(new Font("Inter", Font.BOLD, 24)); // 글꼴 및 크기 설정
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea titleArea = new JTextArea("글제목: " + announcementTitle);
        titleArea.setEditable(false);

        titleArea.setWrapStyleWord(true);
        titleArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea writerArea = new JTextArea("글쓴이: " + announcementWriter);
        writerArea.setEditable(false);
        writerArea.setLineWrap(true);
        writerArea.setWrapStyleWord(true);
        writerArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JTextArea classArea = new JTextArea("수업: " + announcementClass);
        classArea.setEditable(false);
        classArea.setLineWrap(true);
        classArea.setWrapStyleWord(true);
        classArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JPanel infoPanel = new JPanel(new GridLayout(3, 1));
        infoPanel.add(titleArea);
        infoPanel.add(writerArea);
        infoPanel.add(classArea);
        panel.add(infoPanel, BorderLayout.CENTER);

        JTextArea contentArea = new JTextArea(announcementContent);
        contentArea.setEditable(false);
        contentArea.setLineWrap(true);
        contentArea.setWrapStyleWord(true);
        contentArea.setFont(new Font("Inter", Font.PLAIN, 18)); // 글꼴 및 크기 설정

        JScrollPane scrollPane = new JScrollPane(contentArea);
        panel.add(scrollPane, BorderLayout.SOUTH);

        add(panel);
    }
}
