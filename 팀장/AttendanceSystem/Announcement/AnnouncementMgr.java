package Announcement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import config.*;

public class AnnouncementMgr {
    private Connection connection;
    private DefaultTableModel model;

    public AnnouncementMgr(DefaultTableModel model) {
        this.connection = MysqlConnection.getConnection();
        this.model = model;
    }

    public void javaData(String query) {
        try {
        	model.setRowCount(0);
            query = Query.SELECT_JAVA_TABLE;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // 데이터를 JTable 모델에 추가하는 코드
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void cad1Data(String query) {
        try {
        	model.setRowCount(0);
            query = Query.SELECT_CAD1_TABLE;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // 데이터를 JTable 모델에 추가하는 코드
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void cad2Data(String query) {
        try {
        	model.setRowCount(0);
            query = Query.SELECT_CAD2_TABLE;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // 데이터를 JTable 모델에 추가하는 코드
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void computerData(String query) {
        try {
        	model.setRowCount(0);
            query = Query.SELECT_COMPUTER_TABLE;
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            // 데이터를 JTable 모델에 추가하는 코드
            while (resultSet.next()) {
                int announcementNum = resultSet.getInt("announcement_num");
                String announcementTitle = resultSet.getString("announcement_title");
                String announcementWriter = resultSet.getString("announcement_writer");
                String announcementDate = resultSet.getString("announcement_date");
                model.addRow(new Object[]{announcementNum, announcementTitle, announcementWriter, announcementDate});
            }
            if (model.getRowCount() == 0) {
                JOptionPane.showMessageDialog(null, "공지사항이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void deleteRow(int selectedRow) {
        try {
            // 데이터베이스 연결 상태 확인
            if (connection == null || connection.isClosed()) {
                JOptionPane.showMessageDialog(null, "데이터베이스 연결이 닫혔습니다.", "오류", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 삭제할 행의 announcement_num 가져와 DELETE 쿼리 작성
            int announcementNumToDelete = (int) model.getValueAt(selectedRow, 0); // 첫 번째 열을 announcement_num으로 가정
            String query = Query.DELETE_ANNOUNCEMENT;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, announcementNumToDelete);

            // 쿼리 실행
            preparedStatement.executeUpdate();

            // 데이터베이스 연결 종료
            preparedStatement.close();

            // 테이블 모델에서도 삭제된 행 제거
            model.removeRow(selectedRow);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "삭제 중 오류가 발생했습니다.", "오류", JOptionPane.ERROR_MESSAGE);
        }
    }
}
    
    
    
