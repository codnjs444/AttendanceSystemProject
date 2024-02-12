package attendancesystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Main_Menu extends JPanel implements ActionListener {

    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
    private DefaultTableModel model; // model을 필드로 선언
    private JScrollPane scrollPane; // 스크롤 패널도 필드로 선언

    public Main_Menu() {
    	this.model = new DefaultTableModel(new String[]{"순번", "게시글", "작성자", "작성일자"}, 0);
        showData();
        
    }

    private void showData() {
    	 this.model = new DefaultTableModel(new String[]{"순번", "게시글", "작성자", "작성일자"}, 0);
    	 model.setRowCount(0);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1100, 770));
        
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
        
        

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        mainPanel.add(buttonPanel, BorderLayout.NORTH);

        try {
            String query1 = Query.SELECT_CLASS_NAMEUP;
            ResultSet rs1 = QueryExecutor.executeQuery(query1);

            List<String> classNames = new ArrayList<>();
            while (rs1.next()) {
                String className = rs1.getString("class_name");
                classNames.add(className);
            }
            rs1.close();
            for (String className : classNames) {
                JButton button = new JButton(className);
                button.setPreferredSize(new Dimension(275, 82));
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setBorderPainted(false);
                button.addActionListener(this); // ActionListener 추가
                buttonPanel.add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel2, BorderLayout.SOUTH);
        add(mainPanel);
        // 시작 시 바로 출력
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

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource(); // 이벤트가 발생한 버튼 가져오기
        String className = source.getText(); // 버튼의 텍스트(클래스 이름) 가져오기

        // 각 버튼의 동작을 if문으로 구분하여 정의
        if (className.equals(CLASS_NAMES.get(0))) {
            // 첫번째 버튼이 클릭되었을 때의 동작
            System.out.println("1번째 버튼이 클릭되었습니다.");
            
            ////////////////////////////////////////////////////////////////////
            // 버튼 누르면 나오는 동작을 AnnouncementMgr처럼 ex) xxxMgr 구현하고
            // 아래랑 같은 방식으로 표현하면 될거 같아요.
            // SELECT_CAD1_TABLE는 QUERY라는 클래스 파일 보시면 선언했습니다.
            // 
            AnnouncementMgr announcementMgr = new AnnouncementMgr(model);
            announcementMgr.cad1Data(Query.SELECT_CAD1_TABLE);
            ////////////////////////////////////////////////////////////////////
        } else if (className.equals(CLASS_NAMES.get(1))) {
            // 두번째 버튼이 클릭되었을 때의 동작
            System.out.println("2번째 버튼이 클릭되었습니다.");
            AnnouncementMgr announcementMgr = new AnnouncementMgr(model);
            announcementMgr.cad2Data(Query.SELECT_CAD2_TABLE);
        } else if (className.equals(CLASS_NAMES.get(2))) {
            // 세번째 버튼이 클릭되었을 때의 동작
            System.out.println("3번째 버튼이 클릭되었습니다.");
            AnnouncementMgr announcementMgr = new AnnouncementMgr(model);
            announcementMgr.javaData(Query.SELECT_JAVA_TABLE);
        } else if (className.equals(CLASS_NAMES.get(3))) {
            // 네번째 버튼이 클릭되었을 때의 동작
            System.out.println("4번째 버튼이 클릭되었습니다.");
            AnnouncementMgr announcementMgr = new AnnouncementMgr(model);
            announcementMgr.computerData(Query.SELECT_COMPUTER_TABLE);
        }
        // 이런 식으로 필요한 만큼 추가할 수 있습니다.
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Main_Menu());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}