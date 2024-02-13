package attendancesystema;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Locale;

public class StaticMgr extends JFrame {
 
    private Connection connection;
    private DefaultTableModel model;
    private JTextField attendanceRateTextField;
	private int presentCount;
	private int absentCount;
	private int tardyCount;
    public StaticMgr(Connection connection) {
//        super("Attendance Pie Chart");
        this.connection = connection;
        this.attendanceRateTextField = new JTextField();
     // 레이아웃 매니저 설정
        setLayout(new BorderLayout());

        // 출석률 텍스트 필드를 프레임의 남쪽에 추가
        add(attendanceRateTextField, BorderLayout.SOUTH);

        
    }

    public JPanel createJavaStaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - Java반 출석 통계를 가져오는 쿼리에 Java반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_JAVA;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Java반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "Java반 Attendance Statistics",
                dataset,
                false,
                false,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}	", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    
    }

    public JPanel createCad1StaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - CAD1반 출석 통계를 가져오는 쿼리에 CAD1반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_CAD1; // 수정된 부분
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "CAD1급반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "CAD1반 Attendance Statistics",
                dataset,
                true,
                true,
                false);
      
   

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    }
    public JPanel createCad2StaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - CAD1반 출석 통계를 가져오는 쿼리에 CAD1반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_CAD2; // 수정된 부분
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "CAD2급반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "CAD2반 Attendance Statistics",
                dataset,
                true,
                true,
                false);
        

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    }
    public JPanel createComStaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - CAD1반 출석 통계를 가져오는 쿼리에 CAD1반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_COM; // 수정된 부분
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "컴활반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "컴퓨터활용반 Attendance Statistics",
                dataset,
                true,
                true,
                false);
        


        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    }
    
    public JPanel createJavaMonthlyBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = Query.GET_ATTENDANCE_STATISTICS_BY_MONTH;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "Java반");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                int presentCount = resultSet.getInt("present_count");
                int absentCount = resultSet.getInt("absent_count");
                int tardyCount = resultSet.getInt("tardy_count");

                dataset.addValue(presentCount, "출석", String.valueOf(month));
                dataset.addValue(absentCount, "결석", String.valueOf(month));
                dataset.addValue(tardyCount, "지각", String.valueOf(month));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "Java반 Monthly Attendance Statistics", // Chart title
                "Month", // X-axis label
                "Count", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation of the chart
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // 바 차트의 Plot 가져오기
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        // BarRenderer 설정
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // 출석, 결석, 지각에 대한 색상 설정
        renderer.setSeriesPaint(0, new Color(128, 0, 128)); // Dark purple for 출석
        renderer.setSeriesPaint(1, new Color(204, 153, 255)); // Light purple for 결석
        renderer.setSeriesPaint(2, Color.blue); // White for 지각
        
        

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    
    }
    public JPanel createCad1MonthlyBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = Query.GET_ATTENDANCE_STATISTICS_BY_MONTH;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "cad1급반");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                int presentCount = resultSet.getInt("present_count");
                int absentCount = resultSet.getInt("absent_count");
                int tardyCount = resultSet.getInt("tardy_count");

                dataset.addValue(presentCount, "출석", String.valueOf(month));
                dataset.addValue(absentCount, "결석", String.valueOf(month));
                dataset.addValue(tardyCount, "지각", String.valueOf(month));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "cad1급반 Monthly Attendance Statistics", // Chart title
                "Month", // X-axis label
                "Count", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation of the chart
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // 바 차트의 Plot 가져오기
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        // BarRenderer 설정
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // 출석, 결석, 지각에 대한 색상 설정
        renderer.setSeriesPaint(0, new Color(128, 0, 128)); // Dark purple for 출석
        renderer.setSeriesPaint(1, new Color(204, 153, 255)); // Light purple for 결석
        renderer.setSeriesPaint(2, Color.blue); // White for 지각
        
        

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    
    }
    public JPanel createCad2MonthlyBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = Query.GET_ATTENDANCE_STATISTICS_BY_MONTH;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "cad2급반");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                int presentCount = resultSet.getInt("present_count");
                int absentCount = resultSet.getInt("absent_count");
                int tardyCount = resultSet.getInt("tardy_count");

                dataset.addValue(presentCount, "출석", String.valueOf(month));
                dataset.addValue(absentCount, "결석", String.valueOf(month));
                dataset.addValue(tardyCount, "지각", String.valueOf(month));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "cad1급반 Monthly Attendance Statistics", // Chart title
                "Month", // X-axis label
                "Count", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation of the chart
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // 바 차트의 Plot 가져오기
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        // BarRenderer 설정
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // 출석, 결석, 지각에 대한 색상 설정
        renderer.setSeriesPaint(0, new Color(128, 0, 128)); // Dark purple for 출석
        renderer.setSeriesPaint(1, new Color(204, 153, 255)); // Light purple for 결석
        renderer.setSeriesPaint(2, Color.blue); // White for 지각
        
        

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    
    }
    public JPanel createComMonthlyBarChartPanel() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String query = Query.GET_ATTENDANCE_STATISTICS_BY_MONTH;

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "컴활반");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int month = resultSet.getInt("month");
                int presentCount = resultSet.getInt("present_count");
                int absentCount = resultSet.getInt("absent_count");
                int tardyCount = resultSet.getInt("tardy_count");

                dataset.addValue(presentCount, "출석", String.valueOf(month));
                dataset.addValue(absentCount, "결석", String.valueOf(month));
                dataset.addValue(tardyCount, "지각", String.valueOf(month));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JFreeChart chart = ChartFactory.createBarChart(
                "컴퓨터 활용반 Monthly Attendance Statistics", // Chart title
                "Month", // X-axis label
                "Count", // Y-axis label
                dataset, // Dataset
                PlotOrientation.VERTICAL, // Orientation of the chart
                true, // Include legend
                true, // Include tooltips
                false // Include URLs
        );

        // 바 차트의 Plot 가져오기
        CategoryPlot plot = chart.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);

        // BarRenderer 설정
        BarRenderer renderer = (BarRenderer) plot.getRenderer();

        // 출석, 결석, 지각에 대한 색상 설정
        renderer.setSeriesPaint(0, new Color(128, 0, 128)); // Dark purple for 출석
        renderer.setSeriesPaint(1, new Color(204, 153, 255)); // Light purple for 결석
        renderer.setSeriesPaint(2, Color.blue); // White for 지각
        
        

        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        chartPanel.setBackground(Color.WHITE);
        return chartPanel;
    
    }
    public JPanel createJavaoverallStaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - Java반 출석 통계를 가져오는 쿼리에 Java반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_all;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "Java반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "Java반 Attendance Statistics",
                dataset,
                false,
                false,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}	", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    }
    public JPanel createCad1overallStaticChartPanel() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        try {
            // 쿼리 작성 - Java반 출석 통계를 가져오는 쿼리에 Java반 클래스 이름을 넣어줍니다.
            String query = Query.GET_ATTENDANCE_STATISTICS_BY_all;

            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "cad1급반");
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            int presentCount = 0, absentCount = 0, tardyCount = 0;
            if (resultSet.next()) {
                presentCount = resultSet.getInt("present_count");
                absentCount = resultSet.getInt("absent_count");
                tardyCount = resultSet.getInt("tardy_count");
            }

            // 데이터셋에 데이터 추가
            dataset.setValue("출석", presentCount);
            dataset.setValue("결석", absentCount);
            dataset.setValue("지각", tardyCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // 파이 차트 생성
        JFreeChart chart = ChartFactory.createPieChart(
                "Java반 Attendance Statistics",
                dataset,
                false,
                false,
                false);

        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        plot.setBackgroundPaint(Color.WHITE); // White background for the chart

        // 출석률을 레이블에 추가
        double total = presentCount + absentCount + tardyCount;
        double attendanceRate = (presentCount / total) * 100;
        plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}	", new Locale("0.##")));
        attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        return new ChartPanel(chart);
    }
        public JPanel createCad2overallStaticChartPanel() {
        	DefaultPieDataset dataset = new DefaultPieDataset();
        	try {
        		// 쿼리 작성 - Java반 출석 통계를 가져오는 쿼리에 Java반 클래스 이름을 넣어줍니다.
        		String query = Query.GET_ATTENDANCE_STATISTICS_BY_all;
        		
        		PreparedStatement statement = connection.prepareStatement(query);
        		statement.setString(1, "cad2급반");
        		ResultSet resultSet = statement.executeQuery();
        		
        		// 결과 처리
        		int presentCount = 0, absentCount = 0, tardyCount = 0;
        		if (resultSet.next()) {
        			presentCount = resultSet.getInt("present_count");
        			absentCount = resultSet.getInt("absent_count");
        			tardyCount = resultSet.getInt("tardy_count");
        		}
        		
        		// 데이터셋에 데이터 추가
        		dataset.setValue("출석", presentCount);
        		dataset.setValue("결석", absentCount);
        		dataset.setValue("지각", tardyCount);
        	} catch (SQLException e) {
        		e.printStackTrace();
        	}
        	
        	// 파이 차트 생성
        	JFreeChart chart = ChartFactory.createPieChart(
        			"Java반 Attendance Statistics",
        			dataset,
        			false,
        			false,
        			false);
        	
        	PiePlot plot = (PiePlot) chart.getPlot();
        	plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        	plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        	plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        	plot.setBackgroundPaint(Color.WHITE); // White background for the chart
        	
        	// 출석률을 레이블에 추가
        	double total = presentCount + absentCount + tardyCount;
        	double attendanceRate = (presentCount / total) * 100;
        	plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}	", new Locale("0.##")));
        	attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        	return new ChartPanel(chart);
        }
        	public JPanel createComoverallStaticChartPanel() {
        		DefaultPieDataset dataset = new DefaultPieDataset();
        		try {
        			// 쿼리 작성 - Java반 출석 통계를 가져오는 쿼리에 Java반 클래스 이름을 넣어줍니다.
        			String query = Query.GET_ATTENDANCE_STATISTICS_BY_all;
        			
        			PreparedStatement statement = connection.prepareStatement(query);
        			statement.setString(1, "컴활반");
        			ResultSet resultSet = statement.executeQuery();
        			
        			// 결과 처리
        			int presentCount = 0, absentCount = 0, tardyCount = 0;
        			if (resultSet.next()) {
        				presentCount = resultSet.getInt("present_count");
        				absentCount = resultSet.getInt("absent_count");
        				tardyCount = resultSet.getInt("tardy_count");
        			}
        			
        			// 데이터셋에 데이터 추가
        			dataset.setValue("출석", presentCount);
        			dataset.setValue("결석", absentCount);
        			dataset.setValue("지각", tardyCount);
        		} catch (SQLException e) {
        			e.printStackTrace();
        		}
        		
        		// 파이 차트 생성
        		JFreeChart chart = ChartFactory.createPieChart(
        				"Java반 Attendance Statistics",
        				dataset,
        				false,
        				false,
        				false);
        		
        		PiePlot plot = (PiePlot) chart.getPlot();
        		plot.setSectionPaint("출석", new Color(128, 0, 128)); // Dark purple for attendance
        		plot.setSectionPaint("결석", new Color(204, 153, 255)); // Light purple for absent
        		plot.setSectionPaint("지각", Color.WHITE); // White for tardy
        		plot.setBackgroundPaint(Color.WHITE); // White background for the chart
        		
        		// 출석률을 레이블에 추가
        		double total = presentCount + absentCount + tardyCount;
        		double attendanceRate = (presentCount / total) * 100;
        		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}: {2}	", new Locale("0.##")));
        		attendanceRateTextField.setText("출석률: " + String.format("%.2f%%", attendanceRate));
        		return new ChartPanel(chart);
    }
    public static void main(String[] args) {
        Connection connection = null;
        try {
        	 connection = MysqlConnection.getConnection();
             StaticMgr staticMgr = new StaticMgr(connection);
             staticMgr.attendanceRateTextField = new JTextField();
             staticMgr.add(staticMgr.attendanceRateTextField, BorderLayout.SOUTH);
             
             staticMgr.setSize(800, 600);
             staticMgr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             staticMgr.setVisible(true);
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
