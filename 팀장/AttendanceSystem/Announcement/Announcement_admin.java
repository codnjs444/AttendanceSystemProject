package Announcement;
import javax.swing.*;
import config.MyHeaderRenderer;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.*;

public class Announcement_admin extends JPanel implements ActionListener {

    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;

    public Announcement_admin() {
        this.model = new DefaultTableModel(new String[]{"순번", "게시글", "작성자", "작성일자"}, 0);
        this.table = new JTable(model);
        showData();
    }

    private void showData() {
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(1100, 770));

        JPanel buttonPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        CustomButton writeButton = new CustomButton("작성");
        CustomButton detailButton = new CustomButton("보기");
        CustomButton deleteButton = new CustomButton("삭제");

        buttonPanel2.add(detailButton);
        buttonPanel2.add(writeButton);  
        buttonPanel2.add(deleteButton);   

        writeButton.addActionListener(e -> {
            Announcement_write announcementWrite = new Announcement_write();
            announcementWrite.setLocationRelativeTo(null);
            announcementWrite.setVisible(true);
        });
        
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
                    JOptionPane.showMessageDialog(Announcement_admin.this, "공지사항을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });


        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    int confirmDialog = JOptionPane.showConfirmDialog(null, "정말로 삭제하시겠습니까?", "삭제 확인", JOptionPane.YES_NO_OPTION);
                    if (confirmDialog == JOptionPane.YES_OPTION) {
                        AnnouncementMgr announcementMgr = new AnnouncementMgr(model);
                        announcementMgr.deleteRow(selectedRow);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "삭제할 행을 선택해주세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        String[] columns = {"순번", "게시글", "작성자", "작성일자"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1100, 720));
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
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setHeaderRenderer(new MyHeaderRenderer(font));
        }
        scrollPane = new JScrollPane(table);

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
                button.addActionListener(this);
                buttonPanel.add(button);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        mainPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(buttonPanel2, BorderLayout.SOUTH);
        add(mainPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        String className = source.getText();

        AnnouncementMgr announcementMgr = new AnnouncementMgr(model); // AnnouncementMgr 인스턴스 생성
        // 각 버튼에 따라 다른 데이터를 로드하도록 변경
        if (className.equals(CLASS_NAMES.get(0))) {
            announcementMgr.cad1Data(Query.SELECT_CAD1_TABLE);
        } else if (className.equals(CLASS_NAMES.get(1))) {
            announcementMgr.cad2Data(Query.SELECT_CAD2_TABLE);
        } else if (className.equals(CLASS_NAMES.get(2))) {
            announcementMgr.javaData(Query.SELECT_JAVA_TABLE);
        } else if (className.equals(CLASS_NAMES.get(3))) {
            announcementMgr.computerData(Query.SELECT_COMPUTER_TABLE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Announcement_admin());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
