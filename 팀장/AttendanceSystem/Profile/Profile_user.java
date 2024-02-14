package Profile;
import page.*;
import config.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Profile_user extends JPanel {

   private Connection connection;
   String username = UserInfo.getUserID();
   private JTextField nameField;
   private JTextField majorField;
   private JTextField birthField;
   private JTextField genderField;
   private JTextField addressField;
   private JTextField idField;
   private JPasswordField passwordField;
   private JPasswordField newpassField;
   private JPasswordField newpasscomField;
   private JButton saveButton;
   private JButton imgButton;
   private String profile_image;
    private String profile_name;
    private String profile_uploadName;
    private String profile_extension;
    private long profile_Size;

    
    private String hashPassword(String password) {
       try {
           MessageDigest digest = MessageDigest.getInstance("SHA-256");
           byte[] hash = digest.digest(password.getBytes());
           StringBuilder hexString = new StringBuilder();
           for (byte b : hash) {
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
    
   public Profile_user() {
      setPreferredSize(new Dimension(1100, 800));
      setLayout(null);
      setBackground(Color.WHITE);

      JPanel panel = new JPanel();
      panel.setLayout(null);
      panel.setBounds(0, 0, 1200, 950);

      JLabel imgLabel = new JLabel();
      imgLabel.setBounds(200, 100, 120, 150);
      imgLabel.setBorder(new LineBorder(Color.BLACK, 1, true));
      panel.add(imgLabel);
      
      
      
      imgButton = new JButton("사진 선택");
      imgButton.setBounds(201, 255, 120, 20);
      imgButton.setForeground(new Color(255, 255, 255));
      imgButton.setBackground(new Color(133, 91, 221));
      imgButton.setFont(new Font("Inter", Font.BOLD, 15));
      panel.add(imgButton);
      

      
      JLabel nameLabel = new JLabel("이름");
      nameLabel.setBounds(380, 80, 50, 40);
      panel.add(nameLabel);
      nameField = new JTextField(20);
      nameField.setBounds(380, 115, 250, 40);
      nameField.setEditable(false);
      panel.add(nameField);

      
      
      JLabel majorLabel = new JLabel("전공");
      majorLabel.setBounds(700, 80, 50, 40);
      panel.add(majorLabel);
      majorField = new JTextField(20);
      majorField.setBounds(700, 115, 250, 40);
      majorField.setEditable(false);
      panel.add(majorField);

      
      
      JLabel birthLabel = new JLabel("생년월일");
      birthLabel.setBounds(380, 205, 50, 40);
      panel.add(birthLabel);
      birthField = new JTextField(20);
      birthField.setBounds(380, 240, 250, 40);
      birthField.setEditable(false);
      panel.add(birthField);

      
      
      JLabel genderLabel = new JLabel("성별");
      genderLabel.setBounds(700, 205, 50, 40);
      panel.add(genderLabel);
      genderField = new JTextField(20);
      genderField.setBounds(700, 240, 250, 40);
      genderField.setEditable(false);
      panel.add(genderField);

      
      
      JLabel addressLabel = new JLabel("주소");
      addressLabel.setBounds(200, 330, 50, 40);
      panel.add(addressLabel);
      addressField = new JTextField(20);
      addressField.setBounds(200, 365, 750, 40);
      panel.add(addressField);

      
      
      JLabel idLabel = new JLabel("아이디");
      idLabel.setBounds(200, 455, 50, 40);
      panel.add(idLabel);
      idField = new JTextField(20);
      idField.setBounds(200, 490, 350, 40);
      idField.setEditable(false);
      panel.add(idField);

      
      
      JLabel passwordLabel = new JLabel("비밀번호");
      passwordLabel.setBounds(600, 455, 50, 40);
      panel.add(passwordLabel);
      passwordField = new JPasswordField(20);
      passwordField.setBounds(600, 490, 350, 40);
      panel.add(passwordField);

      
      
      JLabel newpassLabel = new JLabel("새 비밀번호");
      newpassLabel.setBounds(200, 580, 100, 40);
      panel.add(newpassLabel);
      newpassField = new JPasswordField(20);
      newpassField.setBounds(200, 615, 350, 40);
      panel.add(newpassField);

      
      
      JLabel newpasscomLabel = new JLabel("새 비밀번호 확인");
      newpasscomLabel.setBounds(600, 580, 100, 40);
      panel.add(newpasscomLabel);
      newpasscomField = new JPasswordField(20);
      newpasscomField.setBounds(600, 615, 350, 40);
      panel.add(newpasscomField);

      
      
      saveButton = new JButton("저장하기");
      saveButton.setBounds(850, 690, 100, 30);
      saveButton.setForeground(new Color(255, 255, 255));
      saveButton.setBackground(new Color(133, 91, 221));
      saveButton.setFont(new Font("Inter", Font.BOLD, 15));
      panel.add(saveButton);

      
      
      panel.add(birthField);

      add(panel);

      setVisible(true);

      try {
         // MySQL 연결 설정
         String url = "jdbc:mysql://localhost:3306/attendancesystem";
         String user = "root";
         String password = "1234";
         connection = DriverManager.getConnection(url, user, password);

         String query = "SELECT user_name, user_major, user_birth, user_gender, user_address, user_id "
               + "FROM user " + "WHERE user_id = '" + username + "'";

         ResultSet resultSet = QueryExecutor.executeQuery(query);

         if (resultSet != null && resultSet.next()) {
            String userName = resultSet.getString("user_name");
            String userMajor = resultSet.getString("user_major");
            String userBirth = resultSet.getString("user_birth");
            String userGender = resultSet.getString("user_gender");
            String userAddress = resultSet.getString("user_address");
            String userId = resultSet.getString("user_id");
            nameField.setText(userName);
            majorField.setText(userMajor);
            birthField.setText(userBirth);
            genderField.setText(userGender);
            addressField.setText(userAddress);
            idField.setText(userId);
            
            
         }

      } catch (SQLException e) {
         e.printStackTrace();
      }
      
      
      
      
      imgButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              JFileChooser fileChooser = new JFileChooser();
              int result = fileChooser.showOpenDialog(null);
              if (result == JFileChooser.APPROVE_OPTION) {
                  File selectedFile = fileChooser.getSelectedFile();
                  profile_image = selectedFile.getAbsolutePath();
                  profile_name = selectedFile.getName();
                  profile_uploadName = selectedFile.getName();
                  profile_extension = getFileExtension(selectedFile);
                  profile_Size = selectedFile.length();
                  
                  ImageIcon imageIcon = new ImageIcon(profile_image);
                  Image image = imageIcon.getImage().getScaledInstance(imgLabel.getWidth(), imgLabel.getHeight(), Image.SCALE_SMOOTH);
                  imgLabel.setIcon(new ImageIcon(image));
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

      saveButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String address = addressField.getText();
              String password1 = new String(passwordField.getPassword());
              String newPass = new String(newpassField.getPassword());
              String newPassCom = new String(newpasscomField.getPassword());

              if (address.isEmpty()) {
                  JOptionPane.showMessageDialog(null, "주소를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
                  return;
              }

              try {
                  String selectQuery = "SELECT user_password FROM user WHERE user_id = ?";
                  PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
                  selectStatement.setString(1, username);

                  ResultSet resultSet = selectStatement.executeQuery();
                  if (resultSet.next()) {
                      String myPassword = resultSet.getString("user_password");
                      if (password1.isEmpty()) {
                          JOptionPane.showMessageDialog(null, "비밀번호를 입력하세요.", "알림", JOptionPane.ERROR_MESSAGE);
                          return;
                      } else if (!hashPassword(password1).equals(myPassword)) {
                          JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", "알림", JOptionPane.ERROR_MESSAGE);
                          return;
                      }
                  }

                  if (newPass.isEmpty() && newPassCom.isEmpty()) {
                      newPass = password1;
                      newPassCom = password1;
                  } else if (!newPass.isEmpty() && !newPass.equals(newPassCom)) {
                      JOptionPane.showMessageDialog(null, "새 비밀번호가 일치하지 않습니다.", "알림", JOptionPane.ERROR_MESSAGE);
                      return;
                  }

                  String updateQuery = "UPDATE user SET user_address = ?, user_password = ?, profile_image = ?, profile_name = ?, profile_uploadName = ?, profile_extension = ?, profile_size = ? WHERE user_id = ?";
                  PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                  updateStatement.setString(1, address);
                  updateStatement.setString(2, hashPassword(newPassCom));
                  updateStatement.setString(3, profile_image);
                  updateStatement.setString(4, profile_name);
                  updateStatement.setString(5, profile_uploadName);
                  updateStatement.setString(6, profile_extension);
                  updateStatement.setLong(7, profile_Size);
                  updateStatement.setString(8, username);

                  int rowsUpdated = updateStatement.executeUpdate();
                  if (rowsUpdated > 0) {
                      JOptionPane.showMessageDialog(null, "회원정보를 수정하였습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
                  } else {
                      JOptionPane.showMessageDialog(null, "회원정보를 수정하지 못하였습니다.", "알림", JOptionPane.ERROR_MESSAGE);
                  }

              } catch (SQLException ex) {
                  ex.printStackTrace();
                  JOptionPane.showMessageDialog(null, "데이터베이스 오류가 발생했습니다.", "알림", JOptionPane.ERROR_MESSAGE);
              }
          }
      });
   }

   
}