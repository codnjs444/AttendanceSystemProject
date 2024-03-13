package mix;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ProfileMgr {
    private Connection connection;
    private DefaultTableModel model;

    public ProfileMgr(DefaultTableModel model) {
        this.connection = MysqlConnection.getConnection();
        this.model = model;
    }
    
    
    
    public void CAD1Data(String query) {
		try {
			String queryJ = Query.SELECT_CAD1STU_TABLE;
			ResultSet resultSet = QueryExecutor.executeQuery(queryJ);
	
			model.setRowCount(0);
			while (resultSet.next()) {
				String user_id = resultSet.getString("user_id");
				String user_major = resultSet.getString("user_major");
				String user_name = resultSet.getString("user_name");
				String user_gender = resultSet.getString("user_gender");
				model.addRow(new Object[] {user_id, user_major, user_name, user_gender });
			}
			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "????????? ????????????.", "?????", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public void CAD2Data(String query) {
		try {
			String queryJ = Query.SELECT_CAD2STU_TABLE;
			ResultSet resultSet = QueryExecutor.executeQuery(queryJ);
	
			model.setRowCount(0);
			while (resultSet.next()) {
				String user_id = resultSet.getString("user_id");
				String user_major = resultSet.getString("user_major");
				String user_name = resultSet.getString("user_name");
				String user_gender = resultSet.getString("user_gender");
				model.addRow(new Object[] {user_id, user_major, user_name, user_gender });
			}
			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "????????? ????????????.", "?????", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    

    public void javaData(String query) {
		try {
			String queryJ = Query.SELECT_JAVASTU_TABLE;
			ResultSet resultSet = QueryExecutor.executeQuery(queryJ);
	
			model.setRowCount(0);
			while (resultSet.next()) {
				String user_id = resultSet.getString("user_id");
				String user_major = resultSet.getString("user_major");
				String user_name = resultSet.getString("user_name");
				String user_gender = resultSet.getString("user_gender");
				model.addRow(new Object[] {user_id, user_major, user_name, user_gender });
			}
			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "????????? ????????????.", "?????", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    
    
    public void comData(String query) {
		try {
			String queryJ = Query.SELECT_COMSTU_TABLE;
			ResultSet resultSet = QueryExecutor.executeQuery(queryJ);
	
			model.setRowCount(0);
			while (resultSet.next()) {
				String user_id = resultSet.getString("user_id");
				String user_major = resultSet.getString("user_major");
				String user_name = resultSet.getString("user_name");
				String user_gender = resultSet.getString("user_gender");
				model.addRow(new Object[] {user_id, user_major, user_name, user_gender });
			}
			if (model.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "????????? ????????????.", "?????", JOptionPane.INFORMATION_MESSAGE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
 
}
