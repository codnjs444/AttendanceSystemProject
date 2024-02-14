package page;
import config.*;

import javax.swing.*;
import java.awt.event.*;
import java.sql.Connection;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.*;
import Announcement.*;
import Attendance.Attendance_admin;
import Home.Home_User;
import Home.Home_admin;
import ProgramMgr.*;


public class Main_user extends JFrame {

    private JPanel currentPanel; // 현재 보여지고 있는 패널을 추적하기 위한 변수
    private String userID;
    public String username;
    private Connection connection;
    private String className;
    private String classTime;

    
    public Main_user(Connection connection, String userID) {
    	this.connection = connection; // 생성자에서 Connection 객체를 전달받음
        this.userID = userID;
        initializeUI();
    }

    
    
    private void initializeUI() {
        setTitle("Attendance_System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1440, 1024);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
        JLayeredPane layeredPane = new JLayeredPane();
        getContentPane().add(layeredPane);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(133, 91, 221));
        topPanel.setPreferredSize(new Dimension(770, 200));
        topPanel.setLayout(new BorderLayout());
        layeredPane.add(topPanel, JLayeredPane.DEFAULT_LAYER);
        topPanel.setBounds(300, 0, 1100, 130);

        JLabel toplabel = new JLabel("     동의대 학생 교육원 프로그램");
        toplabel.setForeground(Color.white);
        toplabel.setFont(new Font("Inter", Font.BOLD, 30));
        topPanel.add(toplabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 50));
        buttonPanel.setOpaque(false);
        topPanel.add(buttonPanel, BorderLayout.EAST);

        JButton button1 = new JButton("로그아웃");
        button1.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(null, "로그아웃 하시겠습니까?", "로그아웃", JOptionPane.YES_NO_OPTION);
            if (result == JOptionPane.YES_OPTION) {
                dispose(); // 현재 프레임(창) 닫기
                new Login().setVisible(true); // 로그인 화면으로 이동
            }
        });
        
        JButton button2 = new JButton("동기화");
//        button2.addActionListener(e -> {
//            refreshAllPanels(); // 모든 화면을 새로고침
//        });

        buttonPanel.add(button1);
        buttonPanel.add(button2);
        button1.setBackground(Color.WHITE);
        button1.setFont(new Font("Inter", Font.PLAIN, 18));
        button1.setBorderPainted(false);
        button2.setBackground(Color.WHITE);
        button2.setFont(new Font("Inter", Font.PLAIN, 18));
        button2.setBorderPainted(false);

        JLabel imgLabel = new JLabel();
        ImageIcon icon = new ImageIcon(Main_user.class.getResource("due.PNG"));
        Image img = icon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon updateIcon = new ImageIcon(img);
        imgLabel.setIcon(updateIcon);
        imgLabel.setBounds(85, 10, 150, 150);
        layeredPane.add(imgLabel, JLayeredPane.PALETTE_LAYER);

        addButtons(layeredPane, imgLabel.getY() + imgLabel.getHeight() + 20);

        setVisible(true);
    }

    private void addButtons(JLayeredPane layeredPane, int initialY) {
        int width = 280;
        int height = 80;
        
        String[] buttonNames = {"홈", "강의 정보", "출결 관리", "상담 신청", "채팅창", "프로필 수정", "청소 당번", "공지 사항", "퇴실"};

        for (int i = 0; i < buttonNames.length; i++) {
            JButton button = CustomButton.createButton(buttonNames[i], Color.WHITE, Color.BLACK, null); // 기본값 설정
            int xCoordinate = 30;
            int yCoordinate = initialY + (height + 0) * i;
            button.setBounds(xCoordinate, yCoordinate, width, height);
            layeredPane.add(button, JLayeredPane.PALETTE_LAYER);

            if (i == buttonNames.length - 1) {
                button.setBackground(new Color(133, 91, 221));
                button.setForeground(Color.WHITE);  
                
                
                button.addActionListener(e -> {
                    int result = JOptionPane.showConfirmDialog(null, "퇴실을 등록하시겠습니까?", "알림", JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                    	// 퇴근 시간 추가
                    	username = UserInfo.getUserID();
                    	AttendanceManager.markDeparture(username);
                        JDialog dialog = new JDialog();
                        dialog.setTitle("프로그램 종료"); // 다이얼로그 제목 설정
                        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                        dialog.setSize(200, 200);
                        dialog.setLocationRelativeTo(null); // 화면 중앙에 배치
                        JLabel countLabel = new JLabel();
                        countLabel.setFont(new Font("Arial", Font.BOLD, 100)); // 폰트 크기를 100으로 설정
                        dialog.add(countLabel);

                        Timer timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            int count = 3;

                            @Override
                            public void run() {
                                if (count > 0) {
                                    countLabel.setText(Integer.toString(count));
                                    count--;
                                } else {
                                    timer.cancel(); // 타이머 종료
                                    dialog.dispose(); // 다이얼로그 닫기
                                    JOptionPane.showMessageDialog(null, "프로그램이 종료됩니다."); // 종료 메시지 표시
                                    System.exit(0); // 프로그램 종료
                                }
                            }
                        }, 0, 1000); // 1초마다 실행

                        dialog.setVisible(true);
                    }
                });
            
            } else if (i == buttonNames.length - 2) {
                button.addActionListener(e -> showPanel(new Announcement_stu(), layeredPane));
            } else if (i == buttonNames.length - 3) {
//                button.addActionListener(e -> showPanel(new (), layeredPane));  
            } else if (i == buttonNames.length - 4) {
//              button.addActionListener(e -> showPanel(new (), layeredPane));    
            } else if (i == buttonNames.length - 5) {
//              button.addActionListener(e -> showPanel(new (), layeredPane));      
            } else if (i == buttonNames.length - 6) {
//              button.addActionListener(e -> showPanel(new (), layeredPane));  
            } else if (i == buttonNames.length - 7) {
            	  button.addActionListener(e -> {
            	        int result = JOptionPane.showConfirmDialog(null, "권한이 없습니다.", "알림", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE);
            	    });
            } else if (i == buttonNames.length - 9) {
            	username = UserInfo.getUserID();
            	className = AttendanceManager.getClassNameByUsername(username);
            	classTime = AttendanceManager.saveClassTime(className);
            	AttendanceManager.updateAttendanceStatus(username, classTime); 
            	button.addActionListener(e -> showPanel(new Home_User(connection,userID), layeredPane));
            	 
            }
        }
    }
    // 새로운 패널을 보여주는 메서드
    private void showPanel(JPanel panel, JLayeredPane layeredPane) {
        // 현재 보여지고 있는 패널을 숨김
        if (currentPanel != null) {
            currentPanel.setVisible(false);
        }
        // 새로운 패널을 추가하고 보여줌
        currentPanel = panel;
        panel.setBounds(300, 130, 1100, 770); // 패널의 위치와 크기 지정
        layeredPane.add(panel, JLayeredPane.MODAL_LAYER);
        panel.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
        	Connection connection = MysqlConnection.getConnection(); // 적절한 방식으로 Connection 객체를 가져옴
            new Main_user(connection, "사용자ID");
            ///////////////////////////////////
           
        
        });
    }
}