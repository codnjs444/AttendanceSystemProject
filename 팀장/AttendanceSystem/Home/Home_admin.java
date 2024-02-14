package Home;
import config.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home_admin extends JPanel {
    private JPanel buttonPanel; // classname 버튼이 들어갈 패널
    private JPanel contentPanel; // 내용을 표시할 패널
    private JPanel statisticsButtonPanel; // 일, 월, 총 버튼이 들어갈 패널
    private JLabel placeholderLabel; // 내용이 표시될 때 사용할 레이블

    public Home_admin() {
        setLayout(new BorderLayout());

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        add(buttonPanel, BorderLayout.NORTH);

        statisticsButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        add(statisticsButtonPanel, BorderLayout.CENTER);

        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        add(scrollPane, BorderLayout.SOUTH);

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
        statisticsButtonPanel.removeAll();

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
        statisticsButtonPanel.add(panel);

        revalidate();
        repaint();
    }

    private JPanel createJavaStaticChartPanel() {
        JPanel javaStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createJavaStaticChartPanel();
        javaStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return javaStaticChartPanel;
    }

    private JPanel createCad1StaticChartPanel() {
        JPanel cad1StaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad1StaticChartPanel();
        cad1StaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad1StaticChartPanel;
    }

    private JPanel createCad2StaticChartPanel() {
        JPanel cad2StaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad2StaticChartPanel();
        cad2StaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad2StaticChartPanel;
    }

    private JPanel createComStaticChartPanel() {
        JPanel comStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createComStaticChartPanel();
        comStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return comStaticChartPanel;
    }

    private JPanel createJavaMonthlyBarChartPanel() {
        JPanel javaMonthlyBarChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createJavaMonthlyBarChartPanel();
        javaMonthlyBarChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return javaMonthlyBarChartPanel;
    }

    private JPanel createCad1MonthlyBarChartPanel() {
        JPanel cad1MonthlyBarChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad1MonthlyBarChartPanel();
        cad1MonthlyBarChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad1MonthlyBarChartPanel;
    }

    private JPanel createCad2MonthlyBarChartPanel() {
        JPanel cad2MonthlyBarChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad2MonthlyBarChartPanel();
        cad2MonthlyBarChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad2MonthlyBarChartPanel;
    }

    private JPanel createComMonthlyBarChartPanel() {
        JPanel comMonthlyBarChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createComMonthlyBarChartPanel();
        comMonthlyBarChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return comMonthlyBarChartPanel;
    }

    private JPanel createJavaoverallStaticChartPanel() {
        JPanel javaOverallStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createJavaoverallStaticChartPanel();
        javaOverallStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return javaOverallStaticChartPanel;
    }

    private JPanel createCad1overallStaticChartPanel() {
        JPanel cad1OverallStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad1overallStaticChartPanel();
        cad1OverallStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad1OverallStaticChartPanel;
    }

    private JPanel createCad2overallStaticChartPanel() {
        JPanel cad2OverallStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createCad2overallStaticChartPanel();
        cad2OverallStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return cad2OverallStaticChartPanel;
    }

    private JPanel createComoverallStaticChartPanel() {
        JPanel comOverallStaticChartPanel = new Home_admin_Mgr(MysqlConnection.getConnection()).createComoverallStaticChartPanel();
        comOverallStaticChartPanel.setBorder(BorderFactory.createEmptyBorder());
        return comOverallStaticChartPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Statistics Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new Home_admin());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}