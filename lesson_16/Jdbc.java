package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Jdbc {
   public static List<DataSQL> getDBMailData() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/mail_data?useSSL=false&serverTimezone=UTC";
        String login = "root";
        String password = "28101990novopolotsk";
        String query = "SELECT user.id,user.login,user.password,mailurl.url,api_keys.apiKey FROM user,mailurl,api_keys";
        List<DataSQL> mailDataBase = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(url, login, password)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                DataSQL mailData = new DataSQL();
                mailData.setUrl(resultSet.getString("url"));
                mailData.setLogin(resultSet.getString("login"));
                mailData.setPassword(resultSet.getString("password"));
                mailData.setApiKey(resultSet.getString("apiKey"));
                mailDataBase.add(mailData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return mailDataBase;
    }
}
