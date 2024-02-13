package mix;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame {

    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/attendancesystem";
    private final String USER = "root";
    private final String PASS = "1234";
    private static String  userID;
    
    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    public String getUserID() {
        return userID;
    }
    
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
    
    public int getUserIDValueFromDatabase(String userID) {
        try {
            Class.forName(JDBC_DRIVER);
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            String sql = "SELECT user_idValue FROM user WHERE user_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, userID);

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

    public Login() {
        setTitle("어서오세요");
        setSize(1440, 1024);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());

        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(133, 91, 221));
        topPanel.setPreferredSize(new Dimension(0, 120));
        topPanel.setLayout(new BorderLayout());
        panel.add(topPanel, BorderLayout.NORTH);
        
        JLabel topLabel = new JLabel("Welcome");
        topLabel.setFont(new Font("Inter", Font.BOLD, 40));
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setForeground(Color.WHITE); // 흰색으로 설정
        topPanel.add(topLabel, BorderLayout.CENTER);
        
        
        JPanel formPanel = new JPanel(null);
        formPanel.setBackground(Color.WHITE);
        
        JLabel titleLabel = new JLabel("학생 출결 관리 시스템");
        titleLabel.setBounds(820, 250, 425, 50);
        titleLabel.setFont(new Font("SanSerif", Font.PLAIN, 40));

        
        JLabel idLabel = new JLabel("아이디");
        JTextField idField = new JTextField(20);
        idLabel.setBounds(810, 320, 425, 30);
        idField.setBounds(810, 370, 425, 30);

        JLabel passwordLabel = new JLabel("비밀번호");
        JPasswordField passwordField = new JPasswordField(100);
        passwordLabel.setBounds(810, 470, 425, 30);
        passwordField.setBounds(810, 520, 425, 30);

        
        
        JButton loginButton = new JButton("로그인");
        JButton registerButton = new JButton("회원가입");
        loginButton.setBounds(810, 640, 425, 50);
        registerButton.setBounds(810, 750, 425, 50);

        
        
        panel.setBackground(Color.WHITE);

        loginButton.setForeground(new Color(255, 255, 255));
        registerButton.setForeground(new Color(255, 255, 255));
        loginButton.setBackground(new Color(133, 91, 221));
        registerButton.setBackground(new Color(133, 91, 221));
        loginButton.setFont(new Font("Inter", Font.BOLD, 30));
        registerButton.setFont(new Font("Inter", Font.BOLD, 30));

        panel.add(titleLabel);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);
        panel.add(registerButton);

        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(Login.class.getResource("duelogin.PNG"));
        imgLabel.setIcon(icon);
        imgLabel.setBounds(600, 270, 450, 450); // 이미지 크기 및 위치 설정
        panel.add(imgLabel);

        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userID = idField.getText();
                String password = new String(passwordField.getPassword());

                boolean success = authenticate(userID, password);

                if (success) {
                    int userIDValue = getUserIDValueFromDatabase(userID);
                    String username = idField.getText();
                    UserInfo.setUserID(username);
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
            pstmt.setString(2, hashPassword(password)); // 해싱된 비밀번호 사용

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