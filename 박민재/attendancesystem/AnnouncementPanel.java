package attendancesystem;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AnnouncementPanel extends JPanel implements ActionListener {
    private DefaultTableModel model; // model을 필드로 선언
    private JScrollPane scrollPane; // 스크롤 패널도 필드로 선언
    private Connection connection;

    public AnnouncementPanel() {
        connectToDatabase(); // 데이터베이스 연결
        showData();
    }

    private void showData() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1100, 770));

        // 버튼 생성
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 4));
        buttonPanel.setPreferredSize(new Dimension(1100, 50));

        // 버튼 생성 및 패널에 추가
        String[] buttonNames = {"JAVA반", "CAD1급반", "CAD2급반", "컴활반"};
        for (String name : buttonNames) {
            JButton button = new JButton(name);
            button.setContentAreaFilled(false);
            button.setBorderPainted(false);
            button.setFont(new Font("Inter", Font.PLAIN, 18));
            button.setForeground(Color.BLACK);
            button.setBackground(Color.WHITE);
            button.addActionListener(this); // ActionListener 추가
            buttonPanel.add(button);
        }

        // 버튼 담을 버튼 panel
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // 글쓰기 버튼
        JButton writeButton = new JButton("글쓰기");
        writeButton.setContentAreaFilled(false); // 버튼의 내용 영역 채우기 비활성화
        writeButton.setBorderPainted(false); // 버튼의 테두리 그리기 비활성화
        writeButton.setFont(new Font("Inter", Font.PLAIN, 18)); // 버튼의 폰트 설정
        writeButton.setForeground(Color.BLACK); // 버튼의 텍스트 색상 설정
        writeButton.setBackground(Color.WHITE); // 버튼의 배경색을 흰색으로 설정
        writeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 글쓰기 버튼을 누르면 Announcement_write 클래스로 이동
                JFrame frame = new JFrame("Announcement_write");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//                frame.add(new Announcement_write());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        buttonPanel2.add(writeButton);
        // 삭제 버튼
        JButton deleteButton = new JButton("삭제");
        deleteButton.setContentAreaFilled(false); // 버튼의 내용 영역 채우기 비활성화
        deleteButton.setBorderPainted(false); // 버튼의 테두리 그리기 비활성화
        deleteButton.setFont(new Font("Inter", Font.PLAIN, 18)); // 버튼의 폰트 설정
        deleteButton.setForeground(Color.BLACK); // 버튼의 텍스트 색상 설정
        deleteButton.setBackground(Color.WHITE); // 버튼의 배경색을 흰색으로 설정
        
        buttonPanel2.add(deleteButton);
        
        // 최종 추가
        mainPanel.add(buttonPanel, BorderLayout.PAGE_START);

        // 표 작성
        String[] columns = {"순번", "게시글", "작성자", "작성일자"};
        model = new DefaultTableModel(columns, 0); // 데이터 없이 빈 모델로 초기화
        JTable table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1100, 720)); // 표의 크기 설정
        Font font = new Font("Inter", Font.PLAIN, 18);
        table.setFont(font);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(new MyHeaderRenderer(font));
        }
        scrollPane = new JScrollPane(table); // 스크롤 패널 초기화

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel2, BorderLayout.SOUTH);

        add(mainPanel);
        // 시작 시 바로 출력
        JavaData();
    }

    static class MyHeaderRenderer implements TableCellRenderer {
        JLabel label;

        public MyHeaderRenderer(Font font) {
            label = new JLabel();
            label.setFont(font);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setBorder(BorderFactory.createEtchedBorder());
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            label.setText(value.toString());
            return label;
        }
    }
    
    // 데이터 베이스 연결
    private void connectToDatabase() {
        try {
            // MySQL 데이터베이스 연결
            String url = "jdbc:mysql://localhost:3306/attendancesystem";
            String username = "root";
            String password = "1234";
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
            // 데이터베이스 연결 실패 시 예외 처리
        }
    }
    
    // JavaData 출력
    private void JavaData() {
        try {
            // SQL 쿼리 작성
            String query = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
                           "FROM announcement " +
                           "WHERE announcement_class = 'java반'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // 데이터를 JTable 모델에 추가
            model.setRowCount(0); // 기존 데이터 초기화
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void Cad1Data() {
        try {
            // SQL 쿼리 작성
            String query = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
                           "FROM announcement " +
                           "WHERE announcement_class = 'cad1반'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // 데이터를 JTable 모델에 추가
            model.setRowCount(0); // 기존 데이터 초기화
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // JavaData출력
    private void Cad2Data() {
        try {
            // SQL 쿼리 작성
            String query = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
                           "FROM announcement " +
                           "WHERE announcement_class = 'cad2반'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // 데이터를 JTable 모델에 추가
            model.setRowCount(0); // 기존 데이터 초기화
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // JavaData출력
    private void ComputerData() {
        try {
            // SQL 쿼리 작성
            String query = "SELECT announcement_num, announcement_title, announcement_writer, announcement_date " +
                           "FROM announcement " +
                           "WHERE announcement_class = '컴활반'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            // 데이터를 JTable 모델에 추가
            model.setRowCount(0); // 기존 데이터 초기화
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(this, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    // 버튼 누를 시 행동들
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand(); // 클릭된 버튼의 명령어 가져오기

        switch (command) {
            case "JAVA반":
                JavaData();
                break;
            case "CAD1급반":
                Cad1Data();
                break;
            case "CAD2급반":
                Cad2Data();
                break;
            case "컴활반":
                ComputerData();
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Announcement Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new AnnouncementPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}