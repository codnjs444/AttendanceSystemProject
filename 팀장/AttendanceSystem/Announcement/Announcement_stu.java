package Announcement;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import config.*;

public class Announcement_stu extends JPanel implements ActionListener {

    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
    private DefaultTableModel model; // model을 필드로 선언
    private JScrollPane scrollPane; // 스크롤 패널도 필드로 선언
    private JTable table;

    public Announcement_stu() {
        this.model = new DefaultTableModel(new String[]{"순번", "게시글", "작성자", "작성일자"}, 0);
        this.table = new JTable(model);
        showData();
    }

    private void showData() {
        this.model = new DefaultTableModel(new String[]{"순번", "게시글", "작성자", "작성일자"}, 0);
        model.setRowCount(0);
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1100, 770));

        // 버튼 담을 버튼 panel
        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton detailButton = new JButton("보기"); // 보기 버튼을 일반 JButton으로 변경

        detailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow(); // 선택된 행 인덱스 가져오기
                if (selectedRow != -1) { // 선택된 행이 있을 경우에만 실행
                    String announcementTitle = (String) table.getValueAt(selectedRow, 1); // 선택된 행의 공지사항 제목 가져오기
                    JFrame frame = new JFrame("Announcement View");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // 창 닫기 버튼 동작 설정
                    frame.add(new Announcement_view(announcementTitle)); // Announcement_view 생성자에 공지사항 제목 전달
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Announcement_stu.this, "공지사항을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        buttonPanel2.add(detailButton);

        // 표 작성
        String[] columns = {"순번", "게시글", "작성자", "작성일자"};
        model = new DefaultTableModel(columns, 0); // 데이터 없이 빈 모델로 초기화
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1100, 720)); // 표의 크기 설정
        Font font = new Font("Inter", Font.PLAIN, 18);
        table.setFont(font);
        
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100); // 순번 열의 너비를 100으로 설정
        columnModel.getColumn(1).setPreferredWidth(500); // 게시글 열의 너비를 500으로 설정
        columnModel.getColumn(2).setPreferredWidth(200); // 작성자 열의 너비를 200으로 설정
        columnModel.getColumn(3).setPreferredWidth(200); // 작성일자 열의 너비를 200으로 설정
        table.setRowHeight(30); // 행의 높이를 30으로 설정
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

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
            frame.add(new Announcement_stu());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
