package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLMovieName {

    public static List<MovieName> getDBMovies() throws ClassNotFoundException {
        String url = "jdbc:mysql://localhost:3306/movie?useSSL=false&serverTimezone=UTC";
        String login = "root";
        String password = "28101990novopolotsk";
        String query = "select * from movie_name;";
        List <MovieName> movies = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url,login,password)) {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    MovieName movie = new MovieName();
                    movie.setName(resultSet.getString("name"));
                    movies.add(movie);
                        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
        return  movies;
    }
}


