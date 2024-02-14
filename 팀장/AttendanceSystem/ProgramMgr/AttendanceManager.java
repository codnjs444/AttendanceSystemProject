package ProgramMgr;

import config.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import config.MysqlConnection;

public class AttendanceManager {

	// 출석 기록을 등록하는 메소드
	public static void markAttendance(String username) {
	    Connection connection = null;
	    try {
	        // MysqlConnection 클래스를 사용하여 연결 설정
	        connection = MysqlConnection.getConnection();

	        // 현재 날짜 가져오기
	        LocalDateTime currentDate = LocalDateTime.now();
	        LocalDate sqlDate = currentDate.toLocalDate();

	        // 오늘 날짜에 대한 출석 기록이 이미 있는지 확인
	        String checkSql = "SELECT COUNT(*) FROM attendance WHERE user_id = ? AND DATE(attendance_in) = ?";
	        PreparedStatement checkStatement = connection.prepareStatement(checkSql);
	        checkStatement.setString(1, username);
	        checkStatement.setDate(2, java.sql.Date.valueOf(sqlDate));
	        ResultSet resultSet = checkStatement.executeQuery();
	        resultSet.next();
	        int rowCount = resultSet.getInt(1);
	        if (rowCount > 0) {
	            System.out.println("이미 출석 기록이 존재합니다.");
	            return; // 출석 기록이 이미 있으면 종료
	        } else {
	            // 새로운 출석 기록 등록
	            String insertSql = "INSERT INTO attendance (user_id, attendance_in, attendance_date) VALUES (?, ?, ?)";
	            PreparedStatement insertStatement = connection.prepareStatement(insertSql);
	            insertStatement.setString(1, username);
	            insertStatement.setTimestamp(2, Timestamp.valueOf(currentDate));
	            insertStatement.setDate(3, java.sql.Date.valueOf(sqlDate)); // attendance_date 값 설정

	            // 실행
	            int rowsInserted = insertStatement.executeUpdate();
	            if (rowsInserted > 0) {
	                System.out.println("출석 기록이 성공적으로 추가되었습니다.");
	            } else {
	                System.out.println("출석 기록 추가에 실패하였습니다.");
	            }
	        }
	    } catch (SQLException e) {
	        System.err.println("SQL exception occurred: " + e.getMessage());
	    } finally {
	        // 연결 종료
	        MysqlConnection.closeConnection(connection);
	    }
	}
    
    // 퇴근 기록을 등록하는 메소드
    public static void markDeparture(String username) {
        Connection connection = null;
        try {
            // MysqlConnection 클래스를 사용하여 연결 설정
            connection = MysqlConnection.getConnection();

            // 현재 날짜 가져오기
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDate sqlDate = currentDate.toLocalDate();

            // 오늘 날짜에 대한 출석 기록이 있는지 확인
            String checkSql = "SELECT COUNT(*) FROM attendance WHERE user_id = ? AND DATE(attendance_in) = ?";
            PreparedStatement checkStatement = connection.prepareStatement(checkSql);
            checkStatement.setString(1, username);
            checkStatement.setDate(2, java.sql.Date.valueOf(sqlDate));
            ResultSet resultSet = checkStatement.executeQuery();
            resultSet.next();
            int rowCount = resultSet.getInt(1);
            if (rowCount > 0) {
                // 퇴근 기록 등록
                String updateSql = "UPDATE attendance SET attendance_out = ? WHERE user_id = ? AND DATE(attendance_in) = ?";
                PreparedStatement updateStatement = connection.prepareStatement(updateSql);
                updateStatement.setTimestamp(1, Timestamp.valueOf(currentDate));
                updateStatement.setString(2, username);
                updateStatement.setDate(3, java.sql.Date.valueOf(sqlDate));
                int rowsUpdated = updateStatement.executeUpdate();
                if (rowsUpdated > 0) {
                    System.out.println("퇴근 기록이 성공적으로 추가되었습니다.");
                } else {
                    System.out.println("퇴근 기록 추가에 실패하였습니다.");
                }
            } else {
                System.out.println("출석 기록이 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        } finally {
            // 연결 종료
            MysqlConnection.closeConnection(connection);
        }
    }

    // 퇴근 시간을 업데이트하는 메소드
    public static void updateAttendanceOut(String username) {
        // 현재 날짜와 시간을 가져옴
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate(); // 현재 날짜만 추출
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateTimeString = now.format(formatter);

        // 데이터베이스 연결 설정
        Connection connection = MysqlConnection.getConnection();

        if (connection != null) {
            try {
                String sql = "UPDATE attendance SET attendance_out = ? WHERE user_id = ? AND attendance_date = ?";
                try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                    pstmt.setString(1, dateTimeString);
                    pstmt.setString(2, username);
                    pstmt.setDate(3, java.sql.Date.valueOf(today)); // 오늘 날짜를 SQL Date로 변환하여 설정
                    pstmt.executeUpdate();
                    System.out.println("Attendance out updated for user: " + username);
                } catch (SQLException e) {
                    System.out.println("Error executing SQL statement: " + e.getMessage());
                }
            } finally {
                MysqlConnection.closeConnection(connection);
            }
        } else {
            System.out.println("Database connection failed.");
        }
    }
    
    // 사용자의 클래스 이름을 가져오는 메소드
    public static String getClassNameByUsername(String username) {
        Connection connection = null;
        String className = null;
        try {
            // MysqlConnection 클래스를 사용하여 연결 설정
            connection = MysqlConnection.getConnection();

            // 사용자의 class_name을 가져오는 SQL 쿼리
            String sql = "SELECT class_name FROM user WHERE user_id = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            if (resultSet.next()) {
                className = resultSet.getString("class_name");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        } finally {
            // 연결 종료
            MysqlConnection.closeConnection(connection);
        }
        return className;
    }
    
    // 클래스 시간을 가져오는 메소드
    public static String saveClassTime(String className) {
        Connection connection = null;
        String classTime = null; // 클래스 시간을 저장할 변수
        try {
            // MysqlConnection 클래스를 사용하여 연결 설정
            connection = MysqlConnection.getConnection();

            // 클래스 이름을 이용하여 class 테이블에서 해당하는 class_time 값을 가져오는 SQL 쿼리
            String sql = "SELECT class_time FROM class WHERE class_name = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, className);
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            if (resultSet.next()) {
                classTime = resultSet.getString("class_time"); // 가져온 class_time 값을 변수에 저장
            } else {
                System.out.println("해당 클래스의 시간을 찾을 수 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        } finally {
            // 연결 종료
            MysqlConnection.closeConnection(connection);
        }
        System.out.println("클래스 시간: " + classTime);
        return classTime; // classTime 변수 반환
    }
    
 // 출석 상태 업데이트
    public static void updateAttendanceStatus(String username, String classTime) {
        Connection connection = null;
        try {
            // MysqlConnection 클래스를 사용하여 연결 설정
            connection = MysqlConnection.getConnection();

            // 오늘 날짜를 가져옴
            LocalDate today = LocalDate.now();

            // 출석 상태 업데이트 SQL 쿼리
            String updateSql = "UPDATE attendance SET attendance_status = ? WHERE user_id = ? AND attendance_date = ?";

            // username과 오늘의 날짜로 조회하여 해당 사용자의 출석 정보 가져오기
            String selectSql = "SELECT attendance_in, attendance_out FROM attendance WHERE user_id = ? AND attendance_date = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setString(1, username);
            selectStatement.setDate(2, Date.valueOf(today));
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                // 출석 정보가 있는 경우
                Timestamp attendanceIn = resultSet.getTimestamp("attendance_in");
                Timestamp attendanceOut = resultSet.getTimestamp("attendance_out");

                String attendanceStatus;

                if (attendanceIn != null) {
                    LocalDateTime attendanceInTime = attendanceIn.toLocalDateTime();
                    LocalDateTime attendanceOutTime = (attendanceOut != null) ? attendanceOut.toLocalDateTime() : null;

                    if (attendanceOutTime != null) {
                        Duration duration = Duration.between(attendanceInTime, attendanceOutTime);
                        Duration halfClassTime = Duration.ofMinutes(Long.parseLong(classTime) / 2);

                        if (duration.compareTo(halfClassTime) >= 0) {
                            attendanceStatus = "출석";
                        } else {
                            attendanceStatus = "지각";
                        }
                    } else {
                        attendanceStatus = "퇴실 미기록"; // 퇴실 미기록 처리
                    }
                } else {
                    attendanceStatus = "결석";
                }

                // 출석 상태 업데이트
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setString(1, attendanceStatus);
                    updateStatement.setString(2, username);
                    updateStatement.setDate(3, Date.valueOf(today));
                    int rowsUpdated = updateStatement.executeUpdate();
                    if (rowsUpdated > 0) {
                        System.out.println(username + "의 출석 상태가 " + attendanceStatus + "로 업데이트되었습니다.");
                    } else {
                        System.out.println("해당 사용자의 출석 상태 업데이트에 실패하였습니다.");
                    }
                }
            } else {
                // 해당 사용자의 출석 정보가 없는 경우
                System.out.println("해당 사용자의 오늘 출석 정보가 없습니다.");
            }
        } catch (SQLException e) {
            System.err.println("SQL exception occurred: " + e.getMessage());
        } finally {
            // 연결 종료
            MysqlConnection.closeConnection(connection);
        }
    }
}