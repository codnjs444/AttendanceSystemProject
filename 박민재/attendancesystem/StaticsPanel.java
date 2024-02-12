package attendancesystem;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StaticsPanel extends JPanel {

    public StaticsPanel() {
        setLayout(new BorderLayout());

        JPanel chartPanel = new JPanel(new BorderLayout());
        add(chartPanel, BorderLayout.CENTER);

        try {
            String query = "SELECT * FROM attendance";
            ResultSet rs = QueryExecutor.executeQuery(query);

            DefaultPieDataset dataset = new DefaultPieDataset();
            int presentCount = 0;
            int totalCount = 0;

            while (rs.next()) {
                String column4Value = rs.getString("attendance_status");
                if (column4Value.equalsIgnoreCase("present")) {
                    presentCount++;
                }
                totalCount++;
            }

            dataset.setValue("Present (" + presentCount + " / " + totalCount + ")", presentCount);
            dataset.setValue("Absent or Tardy (" + (totalCount - presentCount) + " / " + totalCount + ")", totalCount - presentCount);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Attendance Statistics",
                    dataset,
                    true,
                    true,
                    false
            );
            rs.close();

            ChartPanel chartPanelChart = new ChartPanel(chart);
            chartPanelChart.setPreferredSize(new Dimension(800, 600));
            chartPanel.add(chartPanelChart, BorderLayout.CENTER);

            JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
            add(buttonPanel, BorderLayout.NORTH);

            String query1 = "SELECT * FROM class";
            ResultSet rs1 = QueryExecutor.executeQuery(query1);
            
            java.util.List<String> classNames = new java.util.ArrayList<>();
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
            		        buttonPanel.add(button);
            		        if(className == "Class 1") {
            		        	
            		        }
            
            
//
//            if (rs1.next()) {
//            	String[] classNames = new String[4];
//            	int index =0;0000
//                String className = rs1.getString("class_name");
//                
//                
//                for(int i =0; i<classNames.length; i++) {
//                	
//                	JButton button = new JButton(classNames[i]);
//                	button.setPreferredSize(new Dimension(275, 82));
//                	button.setBackground(Color.WHITE);
//                	button.setFont(new Font("Inter", Font.PLAIN, 18));
//                	button.setBorderPainted(false);
//                	buttonPanel.add(button);
//                }
                
//                button1.setPreferredSize(new Dimension(275, 82));
//                button2.setBackground(Color.WHITE);
//                button3.setFont(new Font("Inter", Font.PLAIN, 18));
//                button4.setBorderPainted(false);
//                buttonPanel.add(button1);
//                buttonPanel.add(button2);
//                buttonPanel.add(button3);
//                buttonPanel.add(button4);
//            } else {
//                System.out.println("No class data found.");
//            }
//
//            rs1.close();
            		  }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
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