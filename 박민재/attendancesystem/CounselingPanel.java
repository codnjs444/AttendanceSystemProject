package attendancesystem;

import javax.swing.*;

import com.orsoncharts.graphics3d.swing.Panel3D;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CounselingPanel extends JPanel {
	 JButton btn;
	 JTextField  title_tf, send_tf;
	JTextArea ta;
	JCheckBox cb1, cb2, cb3;
	
	
	public CounselingPanel() {
		
		String query = "SELECT * FROM class";
		ResultSet rs = QueryExecutor.executeQuery(query);
		
		setLayout(new BorderLayout());
      
      //�������� �г� ����
        JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER,50,20));
        JLabel subject_Label = new JLabel("���� ����");
        subject_Label.setFont(new Font("Inter",Font.BOLD,18));
        panel1.add(subject_Label);
        add(panel1,BorderLayout.NORTH);
        
        if(rs!=null) {
	        try {
				while(rs.next()) {
					String className =rs.getString("class_name");
					JCheckBox cb = new JCheckBox(className);
					cb.setFont(new Font("Inter", Font.BOLD, 18));
	                cb.setBorderPainted(false);
	                panel1.add(cb);
				}
				rs.close();
	        }catch (SQLException e) {
					e.printStackTrace();
				}
	 }
        //�������, ����, ���� ��� �г� ����
        JPanel panel2 = new JPanel();
        JLabel title_Label = new JLabel("    ��  ��    ");
        title_tf =new JTextField(55);
        title_Label.setFont(new Font("Inter",Font.BOLD,18));
        title_tf.setFont(new Font("Inter",Font.BOLD,18));
        
        JLabel send_Label = new JLabel(" ���� ���");
        send_tf =new JTextField(55);
        send_Label.setFont(new Font("Inter",Font.BOLD,18));
        send_tf.setFont(new Font("Inter",Font.BOLD,18));
        
        JLabel content_Label = new JLabel("    ��  ��    ");
        ta = new JTextArea(21, 55);
        content_Label.setFont(new Font("Inter",Font.BOLD,18));
        ta.setFont(new Font("Inter",Font.BOLD,18));
      
        panel2.add(title_Label);
        panel2.add(title_tf);
        panel2.add(send_Label);
        panel2.add(send_tf);
        panel2.add(content_Label);
        panel2.add(ta);
        add(panel2,BorderLayout.CENTER);
        
        //��ư �г� ����
        JPanel panel3 =new JPanel();
        btn=new JButton("��û�ϱ�");
        btn.setPreferredSize(new Dimension(200, 50));
        btn.setFont(new Font("Inter", Font.BOLD, 18));
        panel3.add(btn);
        add(panel3,BorderLayout.SOUTH);
        
        //��û�ϱ� ��ư�� ActionListener  �߰�
        btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CounselSaveData();
			}
		});
        setVisible(true);
	}
	
	 
	private void CounselSaveData() {
	//�����ͺ��̽� ����
		try ( Connection conn = MysqlConnection.getConnection()){
			//���� �غ�
			String sql ="INSERT INTO cunseling (counseling_num,counseling_date, counseling_title, counseling_content,user_id)  VALUES(?,?,?,?,?)";
			try (PreparedStatement psmt =conn.prepareStatement(sql)){
				psmt.setString(1, null);
				psmt.setString(2, null);
				psmt.setString(3, title_tf.getText());
				psmt.setString(4,ta.getText());
				psmt.setString(5, null);
				 // ���� ����
	            int rowsAffected = psmt.executeUpdate();
	            if (rowsAffected > 0) {
	                JOptionPane.showMessageDialog(this, "��û�� ���������� ����Ǿ����ϴ�.");
	            } else {
	                JOptionPane.showMessageDialog(this, "��û ���忡 �����߽��ϴ�.");
	            }
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(this, "������ ���� �� ������ �߻��߽��ϴ�.");
		}
	}
        
       

	
	
	
	
	
	
	
	 public static void main(String[] args) {
	        SwingUtilities.invokeLater(() -> {
	            JFrame frame = new JFrame("Counseling Panel");
	            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            frame.add(new CounselingPanel());
	            frame.pack();
	            //frame.setLayout(new BorderLayout());
	            frame.setLocationRelativeTo(null);
	            frame.setVisible(true);
	            
	        });
	   }



}
	
	 
   
	
