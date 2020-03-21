package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JavaDBConnect {
    public static List<DataFromSQL> getDBMailData() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mail_data?useSSL=false&serverTimezone=UTC";
        String login = "root";
        String password = "28101990novopolotsk";
        String query = "SELECT user.id,user.login,user.password FROM user,mailurl";
        List<DataFromSQL> mailDataBase = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                DataFromSQL mailData = new DataFromSQL();
                mailData.setLogin(resultSet.getString("login"));
                mailData.setPassword(resultSet.getString("password"));
                mailDataBase.add(mailData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mailDataBase;
    }
}
