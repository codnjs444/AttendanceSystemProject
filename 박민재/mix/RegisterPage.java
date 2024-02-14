package mix;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class RegisterPage extends JFrame {
	
    private String profile_image;
    JLabel img_Label = new JLabel();
    private String profile_name;
    private String profile_uploadName;
    private String profile_extension;
    private long profile_Size;
	
	public RegisterPage() {
		
		
        JLabel titleLabel = new JLabel("ȯ �� �� �� �� .");
        titleLabel.setBounds(360, 80, 720, 50);
        titleLabel.setFont(new Font("SanSerif", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        JLabel title2Label = new JLabel("�л� ��� ���� ���� �ٷ� �����ϼ��� !");
        title2Label.setBounds(360, 150, 720, 20);
        title2Label.setFont(new Font("SanSerif", Font.BOLD, 20));
        title2Label.setHorizontalAlignment(SwingConstants.CENTER);

        
        
		JFrame otherFrame = new JFrame("ȸ������ ������");
		otherFrame.setSize(1440, 1024);
		otherFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel = new JPanel(null);

		JLabel User_Name_Label = new JLabel("�̸�");
		JTextField User_Name_Field = new JTextField(20);
		User_Name_Label.setBounds(360, 240, 50, 30);
		User_Name_Field.setBounds(360, 270, 330, 30);

		JLabel User_Id_Label = new JLabel("���̵�");
		JTextField User_Id_Field = new JTextField(20);
		User_Id_Label.setBounds(750, 240, 50, 30);
		User_Id_Field.setBounds(750, 270, 330, 30);

		JLabel User_Password_Label = new JLabel("��й�ȣ");
		JPasswordField User_Password_Field = new JPasswordField(100);
		User_Password_Label.setBounds(360, 330, 50, 30);
		User_Password_Field.setBounds(360, 360, 330, 30);

		JLabel Password_C_Label = new JLabel("��й�ȣ Ȯ��");
		JPasswordField Password_C_Field = new JPasswordField(100);
		Password_C_Label.setBounds(750, 330, 100, 30);
		Password_C_Field.setBounds(750, 360, 330, 30);

		JLabel User_Birth_Label = new JLabel("�������");
		JTextField User_Birth_Field = new JTextField(20);
		User_Birth_Label.setBounds(360, 420, 50, 30);
		User_Birth_Field.setBounds(360, 450, 330, 30);

		JLabel User_Gender_Label = new JLabel("����");
		String[] genderOptions = { "---������ �����ϼ���---", "����", "����" };
		JComboBox<String> genderComboBox = new JComboBox<>(genderOptions);
		User_Gender_Label.setBounds(750, 420, 50, 30);
		genderComboBox.setBounds(750, 450, 330, 30);
		String selectedgender = (String) genderComboBox.getSelectedItem();
		if ("---������ �����ϼ���---".equals(selectedgender)) {
			selectedgender = null;
		}

		JLabel User_Address_Label = new JLabel("�ּ�");
		JTextField User_Address_Field = new JTextField(100);
		User_Address_Label.setBounds(360, 510, 50, 30);
		User_Address_Field.setBounds(360, 540, 720, 30);

		JLabel User_phone_Label = new JLabel("��ȭ��ȣ");
		JTextField User_phone_Field = new JTextField(20);
		User_phone_Label.setBounds(360, 600, 50, 30);
		User_phone_Field.setBounds(360, 630, 330, 30);

		JLabel User_major_Label = new JLabel("����");
		JTextField User_major_Field = new JTextField(20);
		User_major_Label.setBounds(750, 600, 50, 30);
		User_major_Field.setBounds(750, 630, 330, 30);

		JLabel Class_name_Label = new JLabel("��������");
		String[] Class_name_Options = { "---���������� �����ϼ���---", "java��", "cad1�޹�", "cad2�޹�", "��Ȱ��" };
		JComboBox<String> ClassComboBox = new JComboBox<>(Class_name_Options);
		Class_name_Label.setBounds(360, 690, 80, 30);
		ClassComboBox.setBounds(360, 720, 330, 30);
		String selectedclass = (String) ClassComboBox.getSelectedItem();
		if ("---���������� �����ϼ���---".equals(selectedclass)) {
			selectedclass = null;
		}

		JLabel profile_img_Label = new JLabel("������ �����ϼ���");
		profile_img_Label.setBounds(750, 690, 150, 30);

		JLabel profile_img_1Label = new JLabel("���� �԰� (132 * 170)");
		profile_img_1Label.setFont(new Font("SanSerif", Font.BOLD, 10));
		profile_img_1Label.setBounds(750, 713, 150, 10);

		JButton profile_img_button = new JButton("���ϼ���");
		img_Label.setBounds(880, 700, 132, 170);
		img_Label.setBorder(new LineBorder(Color.BLACK, 1, true));
		profile_img_button.setBounds(750, 730, 90, 25);
		profile_img_button.setFont(new Font("Inter", Font.ITALIC, 12));

		JButton ComButton = new JButton("�����ϱ�");
		ComButton.setBounds(470, 900, 500, 40);
		ComButton.setForeground(new Color(255, 255, 255));
		ComButton.setBackground(new Color(133, 91, 221));
		ComButton.setFont(new Font("Inter", Font.BOLD, 30));
		panel.add(profile_img_Label);
		panel.add(profile_img_1Label);
		panel.add(img_Label);
		panel.add(profile_img_button);

		panel.add(titleLabel);
		panel.add(title2Label);
		panel.add(User_Name_Label);
		panel.add(User_Name_Field);
		panel.add(User_Id_Label);
		panel.add(User_Id_Field);
		panel.add(User_Password_Label);
		panel.add(User_Password_Field);
		panel.add(Password_C_Label);
		panel.add(Password_C_Field);
		panel.add(User_Birth_Label);
		panel.add(User_Birth_Field);
		panel.add(User_Gender_Label);
		panel.add(genderComboBox);
		panel.add(User_Address_Label);
		panel.add(User_Address_Field);
		panel.add(User_phone_Label);
		panel.add(User_phone_Field);
		panel.add(User_major_Label);
		panel.add(User_major_Field);
		panel.add(Class_name_Label);
		panel.add(ClassComboBox);
		panel.add(ComButton);



		profile_img_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("�̹��� ����", "jpg", "jpeg", "png", "gif");
				fileChooser.setFileFilter(filter);

				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            profile_image = selectedFile.getAbsolutePath();
		            profile_name = selectedFile.getName();
		            profile_uploadName = selectedFile.getName();
		            profile_extension = getFileExtension(selectedFile);
		            profile_Size = selectedFile.length();
		            String url =profile_image;
		            //System.out.println(profile_image);
		            
		            try {
                        img_Label.setIcon(new ImageIcon(new URL("file:///" + url)));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
		            
	            	img_Label.setBounds(880, 700, 132, 170);
	            	panel.add(img_Label);
                    img_Label.setVisible(true);
                    panel.revalidate();
                    panel.repaint();;
		        }
			}
			

			private String getFileExtension(File file) {
				String fileName = file.getName();
				int dotIndex = fileName.lastIndexOf('.');
				if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
					return fileName.substring(dotIndex + 1).toLowerCase();
				} else {
					return "";
				}
			}
		});
		
		

		ComButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				
		        String password = new String(User_Password_Field.getPassword());
		        String encryptedPassword = hashPassword(password);
		        
				dispose();

				String username = User_Name_Field.getText();
				String Id = User_Id_Field.getText();
				String password1 = new String(User_Password_Field.getPassword());
				String confirmPassword = new String(Password_C_Field.getPassword());
				String birth = User_Birth_Field.getText();
				String selectedgender = (String) genderComboBox.getSelectedItem();
				if ("---������ �����ϼ���---".equals(selectedgender)) {
					JOptionPane.showMessageDialog(null, "������ �����ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String gender = selectedgender != null ? selectedgender.toString() : "";
				String address = User_Address_Field.getText();
				String phone = User_phone_Field.getText();
				String major = User_major_Field.getText();
				String selectedclass = (String) ClassComboBox.getSelectedItem();
				if ("---���������� �����ϼ���---".equals(selectedclass)) {
					JOptionPane.showMessageDialog(null, "���������� �����ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String class_name = selectedclass != null ? selectedclass.toString() : "";

				if (username.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�̸��� �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (Id.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���̵� �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (password1.isEmpty()) {
					JOptionPane.showMessageDialog(null, "��й�ȣ�� �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (!password1.equals(confirmPassword)) {
					JOptionPane.showMessageDialog(null, "��й�ȣȮ���� ���� �Է����ּ���", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (birth.isEmpty()) {
					JOptionPane.showMessageDialog(null, "��������� �Է��� �ּ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (gender == null || gender.isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �����ϼ���", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (address.isEmpty()) {
					JOptionPane.showMessageDialog(null, "�ּҸ� �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (phone.isEmpty()) {
					JOptionPane.showMessageDialog(null, "��ȭ��ȣ�� �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (major.isEmpty()) {
					JOptionPane.showMessageDialog(null, "������ �Է��ϼ���.", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				if (class_name == null || class_name.isEmpty()) {
					JOptionPane.showMessageDialog(null, "���������� �����ϼ�", "�˸�", JOptionPane.ERROR_MESSAGE);
					return;
				}

				try {
		            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/attendancesystem",
		                    "root", "1234");
		            PreparedStatement pstmt = conn.prepareStatement(
		                "SELECT * FROM user WHERE user_id = ? AND user_password = ? AND user_name = ? AND user_birth = ? AND user_gender = ? AND user_address = ? AND user_phoneNum = ? AND user_major = ? AND class_name = ? AND user_idValue = ? "
		                + "AND profile_image = ? AND profile_name = ? AND profile_uploadName = ? AND profile_extension = ? AND profile_size = ?");
		            pstmt.setString(1, Id);
		            pstmt.setString(2, encryptedPassword); // ��ȣȭ�� ��й�ȣ ���
		            pstmt.setString(3, username);
		            pstmt.setString(4, birth);
		            pstmt.setString(5, gender);
		            pstmt.setString(6, address);
		            pstmt.setString(7, phone);
		            pstmt.setString(8, major);
		            pstmt.setString(9, class_name);
		            pstmt.setString(10, "0");
		            pstmt.setString(11, profile_image);
		            pstmt.setString(12, profile_name);
		            pstmt.setString(13, profile_uploadName);
		            pstmt.setString(14, profile_extension);
		            pstmt.setLong(15, profile_Size);

					if (pstmt.executeQuery().next()) {
						JOptionPane.showMessageDialog(null, "�̹� ���Ե� ���̵� �Դϴ�.", "�˸�", JOptionPane.ERROR_MESSAGE);
						return;
					} else {
						String sql = "INSERT INTO user (user_id, user_password, user_name, user_birth, user_gender, user_address, user_phoneNum, user_major, class_name, user_idValue, profile_image, profile_name, profile_uploadName, profile_extension, profile_size) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
						PreparedStatement pstmt1 = conn.prepareStatement(sql);
						pstmt1.setString(1, Id);
						pstmt1.setString(2, encryptedPassword);
						pstmt1.setString(3, username);
						pstmt1.setString(4, birth);
						pstmt1.setString(5, gender);
						pstmt1.setString(6, address);
						pstmt1.setString(7, phone);
						pstmt1.setString(8, major);
						pstmt1.setString(9, class_name);
						pstmt1.setString(10, "0");
						pstmt1.setString(11, profile_image);
						pstmt1.setString(12, profile_name);
						pstmt1.setString(13, profile_uploadName);
						pstmt1.setString(14, profile_extension);
						pstmt1.setLong(15, profile_Size);
						int rowsAffected = pstmt1.executeUpdate();
						if (rowsAffected > 0) {
							JOptionPane.showMessageDialog(null, "ȸ������ ����", "�˸�", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(null, "ȸ������ ����", "�˸�", JOptionPane.ERROR_MESSAGE);
						}
					}
					conn.close();
				} catch (SQLException ex) {
		            ex.printStackTrace();
		            JOptionPane.showMessageDialog(null, "ȸ�����Կ� �����Ͽ����ϴ�.", "�˸�", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        Login loginPage = new Login();
		        loginPage.setVisible(true);
		    }
		});
		
		
		otherFrame.add(panel);
		otherFrame.setLocationRelativeTo(null);
		otherFrame.setVisible(true);
		panel.setBackground(Color.WHITE);

	}
	
	private String hashPassword(String password) {
	    try {
	        MessageDigest md = MessageDigest.getInstance("SHA-256");
	        byte[] hashedPassword = md.digest(password.getBytes());
	        StringBuilder hexString = new StringBuilder();
	        for (byte b : hashedPassword) {
	            String hex = Integer.toHexString(0xff & b);
	            if (hex.length() == 1) hexString.append('0');
	            hexString.append(hex);
	        }
	        return hexString.toString();
	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RegisterPage();
			}
		});
	}

    public ImageIcon getImgLabelIcon() {
        return (ImageIcon) img_Label.getIcon();
    }
}