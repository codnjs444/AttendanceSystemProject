package ProgramMgr;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Updater_attendance {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/attendancesystem";
    private static final String DB_USER = "your_username";
    private static final String DB_PASSWORD = "your_password";

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
        // 매일 00:00에 실행되도록 스케줄링
        executor.scheduleAtFixedRate(Updater_attendance::updateAttendance, getTimeUntilMidnight(), 1, TimeUnit.DAYS);
    }

    private static long getTimeUntilMidnight() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime nextMidnight = LocalDateTime.of(currentDateTime.toLocalDate().plusDays(1), LocalTime.MIDNIGHT);
        return Duration.between(currentDateTime, nextMidnight).toMillis();
    }

    private static void updateAttendance() {
        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            // 매일 사용자 데이터 가져오기
            LocalDate currentDate = LocalDate.now();
            if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                Map<Integer, String> users = getUsers(conn, currentDate);
                for (int userId : users.keySet()) {
                    // 출석 테이블에 삽입
                    insertAttendance(conn, userId, currentDate);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Map<Integer, String> getUsers(Connection conn, LocalDate date) throws SQLException {
        Map<Integer, String> users = new HashMap<>();
        String query = "SELECT user_id FROM user WHERE WEEKDAY(?) NOT IN (5, 6)";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setDate(1, java.sql.Date.valueOf(date));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                users.put(userId, "");
            }
        }
        return users;
    }

    private static void insertAttendance(Connection conn, int userId, LocalDate date) throws SQLException {
        String query = "INSERT INTO attendance (user_id, date, status) VALUES (?, ?, '출석')";
        try (PreparedStatement statement = conn.prepareStatement(query)) {
            statement.setInt(1, userId);
            statement.setDate(2, java.sql.Date.valueOf(date));
            statement.executeUpdate();
        }
    }
}