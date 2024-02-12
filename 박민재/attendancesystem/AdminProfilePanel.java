package attendancesystem;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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

import attendancesystem.QueryExecutor;
import attendancesystem.StaticsPanel;

public class AdminProfilePanel extends JPanel {

	private DefaultTableModel model;
	private JScrollPane scrollPane;
	private Connection connection;

	public AdminProfilePanel() {
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
		setLayout(new BorderLayout());

		try {
			JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 0, 0));
			// buttonPanel이라는 JPanel을 생성(왼 -> 오 방향 정렬,오른쪽정렬,수직수평간격 0)
			add(buttonPanel, BorderLayout.NORTH);
			// 위에서 만든 buttonPanel을 북쪽으로 배치해서 추가

			String query = "SELECT * FROM class";
			// query문 실행 (class라는 테이블에서 가져옴)
			ResultSet rs = QueryExecutor.executeQuery(query);
			// QueryExecutor라는 class에서 executeQuery라는 메서드를가져와서 query문 사용하고 그 결과를 rs에 저장

			java.util.List<String> classNames = new java.util.ArrayList<>();
			// String타입의 요소를 저장하는 List 생성. List 이름은 classNames

			while (rs.next()) {
				String className = rs.getString("class_name");
				// 위에 쿼리문을 통해 class라는 테이블을 찾았고 그 테이블의class_name이라는 열의 값을 결과로 받아서 className에 문자열로
				// 저장
				classNames.add(className);
				// 문자열로 받은 className을 위에 만들었던 classNames라는 List에 추가
			} // 다음 결과값이 있으면 계속 반복
			rs.close();
			// 반복이 끝나면 rs닫아주고

			for (String className : classNames) { // list에 있는 이름을 className으로 아래 코드 실행
				JButton button = new JButton(className);
				// 리스트에서 가져온 클래스네임으로 JButton 생성
				button.setPreferredSize(new Dimension(275, 82));
				// 버튼의 크기 지정
				button.setBackground(Color.WHITE);
				// 버튼의 배경색 지정
				button.setFont(new Font("Inter", Font.PLAIN, 18));
				// 버튼의 폰트 설정
				button.setBorderPainted(false);
				// 버튼의 테두리 없애기
				buttonPanel.add(button);
				// 만든 버튼을 buttonPanel에 추가
			}

		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
			// SQL문에서 오류가 발생하면 오류 출력

		} catch (Exception e) {
			e.printStackTrace();
		}

		JPanel mainPanel = new JPanel(new BorderLayout()); // 메인 패널 BorderLayout으로 생성
		mainPanel.setPreferredSize(new Dimension(500, 300)); // 메인 패널의 크기 설정

		String[] columns = {"아이디", "전공", "이름", "성별" }; // 테이블 열 지정
		model = new DefaultTableModel(columns, 0); // DefaultTableModel 객체생성 [model은
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
		add(buttonPanel, BorderLayout.NORTH);

		add(mainPanel);	//메인 패널추가
		Data();	//데이터 가져오는 메서드

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

	
	private void Data() {
		try {
			String query = "SELECT user_id, user_major, user_name, user_gender " + "FROM user "
					+ "WHERE class_name = 'java' AND user_idValue = 0";
			ResultSet resultSet = QueryExecutor.executeQuery(query);

			model.setRowCount(0);
			while (resultSet.next()) {
				String user_id = resultSet.getString("user_id");
				String user_major = resultSet.getString("user_major");
				String user_name = resultSet.getString("user_name");
				String user_gender = resultSet.getString("user_gender");
				model.addRow(new Object[] {user_id, user_major, user_name, user_gender });
			}
			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "회원이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Swing에서 GUI를 생성하고 변경하는 작업을 안전하게 처리하기 위해 사용
			JFrame frame = new JFrame("AdminProfile Panel");
			// Adminprofile Panel이라는이름의 프레임 생성
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// 프레임 닫을때 종료
			frame.add(new AdminProfilePanel());
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