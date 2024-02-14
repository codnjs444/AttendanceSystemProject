package Attendance;
import config.*;
import page.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import ProgramMgr.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;

public class Attendance_admin extends JPanel implements ActionListener {

    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton selectedButton;
    private String classindex;

    public Attendance_admin() {
        showData();
    }

    private void showData() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        String[] columnNames = {"이름", "전공", "아이디", "출근시간", "퇴근시간", "출석현황"};
        model = new DefaultTableModel(columnNames, 0);

        table = new JTable(model);
        table.setPreferredScrollableViewportSize(new Dimension(1100, 570));
        Font font = new Font("Inter", Font.PLAIN, 18);
        table.setFont(font);

        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(100);
        columnModel.getColumn(1).setPreferredWidth(500);
        columnModel.getColumn(2).setPreferredWidth(200);
        columnModel.getColumn(3).setPreferredWidth(200);
        table.setRowHeight(30);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        scrollPane = new JScrollPane(table);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 0));

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

        mainPanel.add(buttonPanel);
        mainPanel.add(scrollPane);

        JPanel editButtonPanel = new JPanel();
        editButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        JButton detailButton = new JButton("수정");
        detailButton.setPreferredSize(new Dimension(100, 30));
        detailButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String userId = (String) table.getValueAt(selectedRow, 2);
                    JFrame frame = new JFrame("Attendance view");
                    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                    frame.add(new Attendance_view(userId));
                    frame.pack();
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(Attendance_admin.this, "학생을 선택하세요.", "알림", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        editButtonPanel.add(detailButton);

        UtilDateModel dateModel = new UtilDateModel();
        dateModel.setValue(new java.util.Date());

        JDatePanelImpl datePanel = new JDatePanelImpl(dateModel);
        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);

        JButton confirmButton = new JButton("확인");
        confirmButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                java.util.Date selectedDate = (java.util.Date) datePicker.getModel().getValue();
                if (selectedDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    String formattedDate = sdf.format(selectedDate);
                    System.out.println("선택된 날짜: " + formattedDate);

                    // 정적 메서드 호출
                    AttendanceMgr.selectData(classindex, formattedDate, table);
                    System.out.println("눌러진 버튼의 텍스트: " + classindex);
                } else {
                    JOptionPane.showMessageDialog(null, "날짜를 먼저 선택해주세요.");
                }
            }
        });

        JPanel dateAndButtonPanel = new JPanel();
        dateAndButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        dateAndButtonPanel.add(datePicker);
        dateAndButtonPanel.add(confirmButton);

        mainPanel.add(dateAndButtonPanel);
        mainPanel.add(scrollPane);
        mainPanel.add(editButtonPanel);
        add(mainPanel);
    }

    private void setSelectedButton(JButton button) {
        if (selectedButton != null) {
            selectedButton.setBackground(Color.WHITE);
        }
        selectedButton = button;
        selectedButton.setBackground(Color.GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource();
        classindex = source.getText();
        System.out.println("눌러진 버튼의 텍스트: " + classindex);

        if (classindex.equals(CLASS_NAMES.get(0))) {
            AttendanceMgr.nowData("cad1급반", table);
        } else if (classindex.equals(CLASS_NAMES.get(1))) {
            AttendanceMgr.nowData("cad2급반", table);
        } else if (classindex.equals(CLASS_NAMES.get(2))) {
            AttendanceMgr.nowData("java반", table);
        } else if (classindex.equals(CLASS_NAMES.get(3))) {
            AttendanceMgr.nowData("컴활반", table);
        }

        setSelectedButton(source);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Attendance_admin());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}