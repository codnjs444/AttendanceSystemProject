package attendancesystem;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserProfilePanel extends JPanel {
	

	private String userID;
    private JTextField imageField;
    private JLabel img_Label;
	private JTextField nameField;
	private JTextField majorField;
	private JTextField birthField;
	private JTextField genderField;
	private JTextField addressField;
	private JTextField idField;
	private JPasswordField passwordField;
	private JTextField newPasswordField;
	private JTextField confirmNewPasswordField;
	private JButton saveButton;

	
	public UserProfilePanel() {
		setPreferredSize(new Dimension(800, 600));
		setLayout(null);
		setBackground(Color.WHITE);
		
		

		imageField = new JTextField();
        imageField.setBounds(100, 100, 180, 220);

        img_Label = new JLabel();
        img_Label.setBounds(100, 100, 180, 220);
        img_Label.setBorder(new LineBorder(Color.BLACK, 1, true));

        add(imageField);
        add(img_Label);

		JLabel nameLabel = new JLabel("이름");
		nameField = new JTextField(20);
		nameLabel.setBounds(340, 120, 50, 40);
		nameField.setBounds(340, 160, 300, 40);

		JLabel majorLabel = new JLabel("전공");
		majorField = new JTextField(20);
		majorLabel.setBounds(700, 120, 50, 40);
		majorField.setBounds(700, 160, 300, 40);

		JLabel birthLabel = new JLabel("생년월일");
		birthField = new JTextField(20);
		birthLabel.setBounds(340, 240, 50, 40);
		birthField.setBounds(340, 280, 300, 40);

		JLabel genderLabel = new JLabel("성별");
		genderField = new JTextField(20);
		genderLabel.setBounds(700, 240, 50, 40);
		genderField.setBounds(700, 280, 300, 40);

		JLabel addressLabel = new JLabel("주소");
		addressField = new JTextField(100);
		addressLabel.setBounds(100, 360, 50, 40);
		addressField.setBounds(100, 400, 900, 40);

		JLabel idLabel = new JLabel("아이디");
		idField = new JTextField(20);
		idLabel.setBounds(100, 480, 50, 40);
		idField.setBounds(100, 520, 400, 40);

		JLabel passwordLabel = new JLabel("비밀번호");
		passwordField = new JPasswordField(100);
		passwordLabel.setBounds(600, 480, 100, 40);
		passwordField.setBounds(600, 520, 400, 40);

		JLabel newPasswordLabel = new JLabel("새 비밀번호");
		newPasswordField = new JTextField(20);
		newPasswordLabel.setBounds(100, 600, 100, 40);
		newPasswordField.setBounds(100, 640, 400, 40);

		JLabel confirmNewPasswordLabel = new JLabel("새 비밀번호 확인");
		confirmNewPasswordField = new JTextField(20);
		confirmNewPasswordLabel.setBounds(600, 600, 100, 40);
		confirmNewPasswordField.setBounds(600, 640, 400, 40);

		saveButton = new JButton("저장하기");
		saveButton.setBounds(900, 70, 100, 30);
		saveButton.setForeground(new Color(255, 255, 255));
		saveButton.setBackground(new Color(133, 91, 221));
		saveButton.setFont(new Font("Inter", Font.BOLD, 15));

		add(imageField);
		add(nameLabel);
		add(nameField);
		add(majorLabel);
		add(majorField);
		add(birthLabel);
		add(birthField);
		add(genderLabel);
		add(genderField);
		add(addressLabel);
		add(addressField);
		add(idLabel);
		add(idField);
		add(passwordLabel);
		add(passwordField);
		add(newPasswordLabel);
		add(newPasswordField);
		add(confirmNewPasswordLabel);
		add(confirmNewPasswordField);
		add(saveButton);
		

	}
	private JFrame getParentFrame() {
        return (JFrame) SwingUtilities.getWindowAncestor(this);
    }
	
    public void setUserID(String userID) {
        this.userID = userID;
        idField.setText(userID);
        if (getParentFrame() != null) {
            Data();
        }
    }
	
	
	private void Data() {
		String query = "SELECT user_name, user_major, user_birth, user_gender, user_address, user_id " +
	               "FROM user " +
	               "WHERE user_id = '" + userID + "'";
		
	
		
		ResultSet resultSet = QueryExecutor.executeQuery(query);
		
		try {
			if (resultSet.next()) {
	            String userName = resultSet.getString("user_name");
	            System.out.println("User Name: " + userName);
	            nameField.setText(userName);
	            nameField.setVisible(true);
				majorField.setText(resultSet.getString("user_major"));
				birthField.setText(resultSet.getString("user_birth"));
				genderField.setText(resultSet.getString("user_gender"));
				addressField.setText(resultSet.getString("user_address"));
				idField.setText(resultSet.getString("user_id"));
	            System.out.println(userID);
			}else {
				System.out.println("No user found with ID: "+ userID);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ImageIcon icon = ((RegisterPage) SwingUtilities.getWindowAncestor(this)).getImgLabelIcon();
        if (icon != null) {
            img_Label.setIcon(icon);
        } else {
            // img_Label에 아이콘이 없을 경우 처리
        }	
		
		
	}
	
	
}