package Profile;
import config.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

public class Profile_admin extends JPanel implements ActionListener {

	 private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Connection connection;

	public Profile_admin() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		
		JPanel mainPanel1 = new JPanel(new BorderLayout());
		JPanel buttonPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
        mainPanel1.add(buttonPanel1, BorderLayout.NORTH);

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
                buttonPanel1.add(button);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		add(mainPanel1, BorderLayout.NORTH);	//클래스 패널 북쪽에 추가

		
		
		
		
		JPanel mainPanel = new JPanel(new BorderLayout()); // 메인 패널 BorderLayout으로 생성
		mainPanel.setPreferredSize(new Dimension(500, 300)); // 메인 패널의 크기 설정


		String[] columns = {"아이디", "전공", "이름", "성별" }; // 테이블 열 지정
		model = new DefaultTableModel(columns, 0); // DefaultTableModel 객체생성 model은
		JTable table = new JTable(model); // model으로 JTable 생성
		table.setRowHeight(60);
		table.setPreferredScrollableViewportSize(new Dimension(400, 20));
		Font font = new Font("Inter", Font.BOLD, 20);

		scrollPane = new JScrollPane(table);	//스크롤 가능하게 scrollpane추가
		mainPanel.add(scrollPane, BorderLayout.CENTER);	//패널을 중앙에 추가

		JButton deleteButton = new JButton("삭제");
		deleteButton.setPreferredSize(new Dimension(90, 40)); //크기 지정
		deleteButton.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();	//행 값 가져오기
			if (selectedRow != -1) {	//만약 행이 선택되어있다면
				String user_id = (String) model.getValueAt(selectedRow, 0);	//선택된 행의 user_id 가져와서
				deleteFromDatabase(user_id); // 데이터베이스에서 삭제
				model.removeRow(selectedRow); // 테이블에서 삭제
			}
		});
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		buttonPanel.add(deleteButton);
		add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel, BorderLayout.CENTER);	//메인 패널추가
		//Data();	//데이터 가져오는 메서드

	}

	private void deleteFromDatabase(String user_id) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem", "root",
					"1234"); // QueryExecutor 클래스의 getConnection() 메서드로 Connection을 가져옴
			if (conn != null) {
				String query = "DELETE FROM user WHERE user_id = '" + user_id + "'";
				System.out.println(query); // 콘솔에 쿼리 출력
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
				// conn.commit(); // 변경 내용을 데이터베이스에 반영
				JOptionPane.showMessageDialog(null, "삭제완료", "알림", JOptionPane.INFORMATION_MESSAGE);
				statement.close();
			} else {
				System.out.println("Connection is null");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
        JButton source = (JButton) e.getSource(); // 이벤트가 발생한 버튼 가져오기
        String className = source.getText();
        
        if (className.equals(CLASS_NAMES.get(0))) {
            // 첫번째 버튼이 클릭되었을 때의 동작
            System.out.println("CAD1급 수강생 입니다.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.CAD1Data(Query.SELECT_CAD1STU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(1))) {
            // 두번째 버튼이 클릭되었을 때의 동작
            System.out.println("CAD2급 수강생 입니다.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.CAD2Data(Query.SELECT_CAD2STU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(2))) {
            // 세번째 버튼이 클릭되었을 때의 동작
            System.out.println("JAVA 수강생 입니다.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.javaData(Query.SELECT_JAVASTU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(3))) {
            // 네번째 버튼이 클릭되었을 때의 동작
            System.out.println("컴활 수강생 입니다.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.comData(Query.SELECT_COMSTU_TABLE);
           
        }
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Swing에서 GUI를 생성하고 변경하는 작업을 안전하게 처리하기 위해 사용
			JFrame frame = new JFrame("AdminProfile Panel");
			// Adminprofile Panel이라는이름의 프레임 생성
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 프레임 닫을때 종료
			frame.add(new Profile_admin());
			// AdminProfilePane라는 이름의 객체(Panel)를 생성
			frame.pack();
			// 프레임을 내부 크기에 맞게 자동조정
			frame.setLocationRelativeTo(null);
			// 프레임을 화면 중앙에 배치
			frame.setVisible(true);
			// 프레임을 화면에 표시
		});
	}
}