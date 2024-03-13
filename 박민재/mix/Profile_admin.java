package mix;

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
                button.addActionListener(this); // ActionListener �߰�
                buttonPanel1.add(button);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

		add(mainPanel1, BorderLayout.NORTH);	//Ŭ���� �г� ���ʿ� �߰�

		
		
		
		
		JPanel mainPanel = new JPanel(new BorderLayout()); // ���� �г� BorderLayout���� ����
		mainPanel.setPreferredSize(new Dimension(500, 300)); // ���� �г��� ũ�� ����


		String[] columns = {"���̵�", "����", "�̸�", "����" }; // ���̺� �� ����
		model = new DefaultTableModel(columns, 0); // DefaultTableModel ��ü���� model��
		JTable table = new JTable(model); // model���� JTable ����
		table.setRowHeight(60);
		table.setPreferredScrollableViewportSize(new Dimension(400, 20));
		Font font = new Font("Inter", Font.BOLD, 20);

		scrollPane = new JScrollPane(table);	//��ũ�� �����ϰ� scrollpane�߰�
		mainPanel.add(scrollPane, BorderLayout.CENTER);	//�г��� �߾ӿ� �߰�

		JButton deleteButton = new JButton("����");
		deleteButton.setPreferredSize(new Dimension(90, 40)); //ũ�� ����
		deleteButton.addActionListener(e -> {
			int selectedRow = table.getSelectedRow();	//�� �� ��������
			if (selectedRow != -1) {	//���� ���� ���õǾ��ִٸ�
				String user_id = (String) model.getValueAt(selectedRow, 0);	//���õ� ���� user_id �����ͼ�
				deleteFromDatabase(user_id); // �����ͺ��̽����� ����
				model.removeRow(selectedRow); // ���̺��� ����
			}
		});
		
		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
		buttonPanel.add(deleteButton);
		add(buttonPanel, BorderLayout.SOUTH);

		add(mainPanel, BorderLayout.CENTER);	//���� �г��߰�
		//Data();	//������ �������� �޼���

	}

	private void deleteFromDatabase(String user_id) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem", "root",
					"1234"); // QueryExecutor Ŭ������ getConnection() �޼���� Connection�� ������
			if (conn != null) {
				String query = "DELETE FROM user WHERE user_id = '" + user_id + "'";
				System.out.println(query); // �ֿܼ� ���� ���
				Statement statement = conn.createStatement();
				statement.executeUpdate(query);
				// conn.commit(); // ���� ������ �����ͺ��̽��� �ݿ�
				JOptionPane.showMessageDialog(null, "�����Ϸ�", "�˸�", JOptionPane.INFORMATION_MESSAGE);
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
        JButton source = (JButton) e.getSource(); // �̺�Ʈ�� �߻��� ��ư ��������
        String className = source.getText();
        
        if (className.equals(CLASS_NAMES.get(0))) {
            // ù��° ��ư�� Ŭ���Ǿ��� ���� ����
            System.out.println("CAD1�� ������ �Դϴ�.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.CAD1Data(Query.SELECT_CAD1STU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(1))) {
            // �ι�° ��ư�� Ŭ���Ǿ��� ���� ����
            System.out.println("CAD2�� ������ �Դϴ�.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.CAD2Data(Query.SELECT_CAD2STU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(2))) {
            // ����° ��ư�� Ŭ���Ǿ��� ���� ����
            System.out.println("JAVA ������ �Դϴ�.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.javaData(Query.SELECT_JAVASTU_TABLE);
            
            
        } else if (className.equals(CLASS_NAMES.get(3))) {
            // �׹�° ��ư�� Ŭ���Ǿ��� ���� ����
            System.out.println("��Ȱ ������ �Դϴ�.");
            ProfileMgr ProfileMgr = new ProfileMgr(model);
            ProfileMgr.comData(Query.SELECT_COMSTU_TABLE);
           
        }
	}
	
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			// Swing���� GUI�� �����ϰ� �����ϴ� �۾��� �����ϰ� ó���ϱ� ���� ���
			JFrame frame = new JFrame("AdminProfile Panel");
			// Adminprofile Panel�̶���̸��� ������ ����
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// ������ ������ ����
			frame.add(new Profile_admin());
			// AdminProfilePane��� �̸��� ��ü(Panel)�� ����
			frame.pack();
			// �������� ���� ũ�⿡ �°� �ڵ�����
			frame.setLocationRelativeTo(null);
			// �������� ȭ�� �߾ӿ� ��ġ
			frame.setVisible(true);
			// �������� ȭ�鿡 ǥ��
		});
	}
}