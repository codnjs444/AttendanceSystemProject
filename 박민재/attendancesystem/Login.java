package attendancesystem;

import javax.swing.*;
import javax.swing.text.StyledEditorKit.BoldAction;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/attendancesystem";
    private final String USER = "root";
    private final String PASS = "1234";
    
    public int getUserIDValueFromDatabase(String id) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT user_idValue FROM user WHERE user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int userIDValue = rs.getInt("user_idValue");
                rs.close();
                pstmt.close();
                conn.close();
                return userIDValue;
            } else {
                rs.close();
                pstmt.close();
                conn.close();
                return -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    public class LoginManager{
    	private	static String userID;
    	
    	public static void setUserID(String id) {
    		userID = id;
    	}
    	
    	public static String getUserID() {
    		return userID;
    	}
    }

   public Login() {
      setTitle("어서오세요");
      setSize(1440, 1024);
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      JPanel panel = new JPanel(null);

      JLabel idLabel = new JLabel("아이디");
      JTextField idField = new JTextField(20);
      idLabel.setBounds(810, 350, 425, 30);
      idField.setBounds(810, 400, 425, 30);

      JLabel passwordLabel = new JLabel("비밀번호");
      JPasswordField passwordField = new JPasswordField(100);
      passwordLabel.setBounds(810, 500, 425, 30);
      passwordField.setBounds(810, 550, 425, 30);

      JButton loginButton = new JButton("로그인");
      JButton registerButton = new JButton("회원가입");
      loginButton.setBounds(810, 670, 425, 50);
      registerButton.setBounds(810, 780, 425, 50);


      
      panel.setBackground(Color.WHITE);

      loginButton.setForeground(new Color(255, 255, 255));
      registerButton.setForeground(new Color(255, 255, 255));
      loginButton.setBackground(new Color(133, 91, 221));
      registerButton.setBackground(new Color(133, 91, 221));
      loginButton.setFont(new Font("Inter", Font.BOLD, 30));
      registerButton.setFont(new Font("Inter", Font.BOLD, 30));
      
      add(panel);
      setLocationRelativeTo(null);
      setVisible(true);
      
      panel.add(idLabel);
      panel.add(idField);
      panel.add(passwordLabel);
      panel.add(passwordField);
      panel.add(loginButton);
      panel.add(registerButton);
      

      loginButton.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
              String id = idField.getText();
              String password = new String(passwordField.getPassword());

              boolean success = authenticate(id, password);

              if (success) {
                  int userIDValue = getUserIDValueFromDatabase(id);
                  if (userIDValue == 0) {
                      Main_user mainPage = new Main_user();
                      JOptionPane.showMessageDialog(null, "어서오세요 유저님", "알림", JOptionPane.INFORMATION_MESSAGE);
                      mainPage.setVisible(true);
                      dispose();
                  } else if (userIDValue == 1) {
                      Main_admin mainPage = new Main_admin();
                      JOptionPane.showMessageDialog(null, "어서오세요 관리자님", "알림", JOptionPane.INFORMATION_MESSAGE);
                      mainPage.setVisible(true);
                      dispose();
                  } else {
                      JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 다시 입력해 주세요", "알림", JOptionPane.ERROR_MESSAGE);
                  }
                  UserProfilePanel userProfilePanel = new UserProfilePanel();
                  userProfilePanel.setUserID(id);
                  System.out.println(id);
              } else {
                  JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 다시 입력해 주세요");
              }
          }
      });

      
      registerButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            dispose();

            RegisterPage registerPage = new RegisterPage();
            registerPage.setVisible(false);
         }
      });
   }

    private boolean authenticate(String id, String password) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT * FROM user WHERE user_id=? AND user_password=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, password);

            ResultSet rs = pstmt.executeQuery();
            boolean result = rs.next();

            rs.close();
            pstmt.close();
            conn.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Login();
            }
        });
    }
}