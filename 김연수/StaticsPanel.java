package attendancesystema;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaticsPanel extends JPanel {
    private JPanel buttonPanel; // classname 버튼이 들어갈 패널
    private JButton selectedButton; // 선택된 classname 버튼을 저장할 변수
    private JPanel contentPanel; // 내용을 표시할 패널
    private JLabel placeholderLabel; // 내용이 표시될 때 사용할 레이블

    public StaticsPanel() {
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        add(buttonPanel, BorderLayout.NORTH);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.CENTER);

        placeholderLabel = new JLabel("여기에 내용을 표시합니다.");
        placeholderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        contentPanel.add(placeholderLabel);

        try {
            Connection connection = MysqlConnection.getConnection();

            String query1 = Query.SELECT_CLASS_NAMES;
            PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
            ResultSet rs1 = preparedStatement1.executeQuery();

            while (rs1.next()) {
                final String className = rs1.getString("class_name");

                JButton button = new JButton(className);
                button.setPreferredSize(new Dimension(275, 82));
                button.setBackground(Color.WHITE);
                button.setFont(new Font("Inter", Font.PLAIN, 18));
                button.setBorderPainted(false);

                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        createStatisticsButtons(className);
                    }
                });

                buttonPanel.add(button);
            }
            rs1.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createStatisticsButtons(String className) {
        contentPanel.removeAll();

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton dayButton = new JButton("일");
        dayButton.setPreferredSize(new Dimension(366, 82));
        dayButton.setBackground(Color.WHITE);
        dayButton.setFont(new Font("Inter", Font.PLAIN, 18));
        dayButton.setBorderPainted(false);
        dayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                if (className.equals("java반")) {
                    contentPanel.add(createJavaStaticChartPanel());
                } else if (className.equals("cad1급반")) {
                    contentPanel.add(createCad1StaticChartPanel());
                } else if (className.equals("cad2급반")) {
                    contentPanel.add(createCad2StaticChartPanel());
                } else if (className.equals("컴활반")) {
                    contentPanel.add(createComStaticChartPanel());
                }
                contentPanel.setBackground(Color.WHITE); // 모든 경우에 대해 배경색 설정
                revalidate();
                repaint();
            }	
        });

        JButton monthButton = new JButton("월");
        monthButton.setPreferredSize(new Dimension(366, 82));
        monthButton.setBackground(Color.WHITE);
        monthButton.setFont(new Font("Inter", Font.PLAIN, 18));
        monthButton.setBorderPainted(false);
        monthButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                if (className.equals("java반")) {
                    contentPanel.add(createJavaMonthlyBarChartPanel());
                } else if (className.equals("cad1급반")) {
                    contentPanel.add(createCad1MonthlyBarChartPanel());
                } else if (className.equals("cad2급반")) {
                    contentPanel.add(createCad2MonthlyBarChartPanel());
                } else if (className.equals("컴활반")) {
                    contentPanel.add(createComMonthlyBarChartPanel());
                }
                contentPanel.setBackground(Color.WHITE); // 배경색 설정
                revalidate();
                repaint();
            }
        });

        JButton totalButton = new JButton("총");
        totalButton.setPreferredSize(new Dimension(366, 82));
        totalButton.setBackground(Color.WHITE);
        totalButton.setFont(new Font("Inter", Font.PLAIN, 18));
        totalButton.setBorderPainted(false);
        totalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contentPanel.removeAll();
                if (className.equals("java반")) {
                    contentPanel.add(createJavaoverallStaticChartPanel());
                } else if (className.equals("cad1급반")) {
                    contentPanel.add(createCad1overallStaticChartPanel());
                } else if (className.equals("cad2급반")) {
                    contentPanel.add(createCad2overallStaticChartPanel());
                } else if (className.equals("컴활반")) {
                    contentPanel.add(createComoverallStaticChartPanel());
                }
                contentPanel.setBackground(Color.WHITE); // 배경색 설정
                revalidate();
                repaint();
            }
        });

        panel.add(dayButton);
        panel.add(monthButton);
        panel.add(totalButton);
        contentPanel.add(panel);

        revalidate();
        repaint();
    }

    private JPanel createJavaStaticChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createJavaStaticChartPanel();
    }

    private JPanel createCad1StaticChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createCad1StaticChartPanel();
    }

    private JPanel createCad2StaticChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createCad2StaticChartPanel();
    }

    private JPanel createComStaticChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createComStaticChartPanel();
    }
    private JPanel createJavaMonthlyBarChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createJavaMonthlyBarChartPanel();
    }
    private JPanel createCad1MonthlyBarChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createCad1MonthlyBarChartPanel();
    }
    private JPanel createCad2MonthlyBarChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createCad2MonthlyBarChartPanel();
    }
    private JPanel createComMonthlyBarChartPanel() {
        return new StaticMgr(MysqlConnection.getConnection()).createComMonthlyBarChartPanel();
    }
    private JPanel createJavaoverallStaticChartPanel() {
    	return new StaticMgr(MysqlConnection.getConnection()).createJavaoverallStaticChartPanel();
    }
    private JPanel createCad1overallStaticChartPanel() {
    	return new StaticMgr(MysqlConnection.getConnection()).createCad1overallStaticChartPanel();
    }
    private JPanel createCad2overallStaticChartPanel() {
    	return new StaticMgr(MysqlConnection.getConnection()).createCad2overallStaticChartPanel();
    }
    private JPanel createComoverallStaticChartPanel() {
    	return new StaticMgr(MysqlConnection.getConnection()).createComoverallStaticChartPanel();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Statistics Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new StaticsPanel());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}