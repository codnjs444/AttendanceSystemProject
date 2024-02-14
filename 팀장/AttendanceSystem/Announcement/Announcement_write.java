package Announcement;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import config.*;

public class Announcement_write extends JFrame {
    private JTextField title;
    private JComboBox<String> writer;
    private JComboBox<String> class_name;
    private Connection connection;
    private final List<String> CLASS_NAMES = Class_Name.CLASS_NAMES;
    private String userId;

    public Announcement_write() {
        setBounds(new Rectangle(600, 0, 450, 280));
        setTitle("게시글등록");
        getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("글제목");
        lblNewLabel.setBounds(12, 25, 57, 15);
        getContentPane().add(lblNewLabel);

        title = new JTextField("글제목입니다.");
        title.setBounds(81, 22, 340, 21);
        getContentPane().add(title);
        title.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("글내용");
        lblNewLabel_1.setBounds(12, 59, 57, 15);
        getContentPane().add(lblNewLabel_1);

        JTextArea textArea = new JTextArea("글내용 글내용..");
        textArea.setLineWrap(true);
        textArea.setRows(5);
        textArea.setBounds(81, 53, 340, 69);
        getContentPane().add(textArea);

        JLabel lblNewLabel_2 = new JLabel("작성자");
        lblNewLabel_2.setBounds(12, 140, 57, 15);
        getContentPane().add(lblNewLabel_2);
        
        JLabel lblNewLabel_3 = new JLabel("수업");
        lblNewLabel_3.setBounds(220, 140, 57, 15);
        getContentPane().add(lblNewLabel_3);

        List<String> writerOptions = fetchUserNamesFromDatabase(Query.Select_ADMIN_ID, "user_name");
        List<String> classOptions = fetchUserNamesFromDatabase(Query.SELECT_CLASS_NAMEUP, "class_name");

        writer = new JComboBox<>(writerOptions.toArray(new String[0]));
        writer.setBounds(81, 137, 116, 21);
        getContentPane().add(writer);
        
        class_name = new JComboBox<>(classOptions.toArray(new String[0]));
        class_name.setBounds(260, 137, 116, 21);
        getContentPane().add(class_name);

        JButton btnWrite = new JButton("작성완료");
      
        btnWrite.setBounds(81, 180, 116, 23);
        btnWrite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String announcementTitle = title.getText();
                String announcementContent = textArea.getText();
                String writerName = (String) writer.getSelectedItem();
                String className = (String) class_name.getSelectedItem();

                Connection connection = MysqlConnection.getConnection();
                if (connection != null) {
                    try {
                        String getUserIdQuery = Query.FIND_USER_ID;
                        PreparedStatement getUserIdStatement = connection.prepareStatement(getUserIdQuery);
                        getUserIdStatement.setString(1, writerName);
                        ResultSet resultSet = getUserIdStatement.executeQuery();

                        if (resultSet.next()) {
                            userId = resultSet.getString("user_id");
                        }

                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDate = dateFormat.format(new Date());

                        String sql = Query.INSERT_ANNOUNCEMENT;
                        PreparedStatement statement = connection.prepareStatement(sql);

                        statement.setString(1, announcementTitle);
                        statement.setString(2, announcementContent);
                        statement.setString(3, writerName);
                        statement.setString(4, className);
                        statement.setString(5, currentDate);
                        statement.setString(6, userId);

                        int rowsAffected = statement.executeUpdate();

                        if (rowsAffected > 0) {
                        	JOptionPane.showMessageDialog(null, "게시글이 성공적으로 등록되었습니다.", "게시글 등록 완료", JOptionPane.INFORMATION_MESSAGE);
                        	setVisible(false);
                        	System.out.println("게시글이 성공적으로 등록되었습니다.");
                        } else {
                            System.out.println("게시글 등록에 실패했습니다.");
                        }

                        statement.close();

                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    } finally {
                        MysqlConnection.closeConnection(connection);
                    }
                } else {
                    System.out.println("데이터베이스 연결 실패");
                }
            }
        });
        getContentPane().add(btnWrite);

        JButton btnClose = new JButton("닫기");
 
        btnClose.setBounds(209, 180, 97, 23);
        btnClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        getContentPane().add(btnClose);  
        setVisible(true);
    }
    
    private List<String> fetchUserNamesFromDatabase(String query, String columnName) {
        List<String> values = new ArrayList<>();

        try {
            this.connection = MysqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String value = resultSet.getString(columnName);
                values.add(value);
            }

            resultSet.close();
            statement.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return values;
    }
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Announcement_write announcementWrite = new Announcement_write(); 
            announcementWrite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Connection connection = MysqlConnection.getConnection(); // 데이터베이스 연결 가져오기
            MysqlConnection.closeConnection(connection); // 데이터베이스 연결 닫기
        });
    }
}
