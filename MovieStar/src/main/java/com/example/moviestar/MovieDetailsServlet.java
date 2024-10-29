package com.example.moviestar;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/movie-details")
public class MovieDetailsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Kết nối đến cơ sở dữ liệu
        String url = "jdbc:mysql://localhost:3306/Netflix";
        String username = "root";
        String password = "password";

        List<Movies> moviesList = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM movie";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // Đọc dữ liệu từ cơ sở dữ liệu
            while (resultSet.next()) {
                Movies movie = new Movies();
                movie.setId_movies(resultSet.getInt("id_movie"));
                movie.setName_movies(resultSet.getString("name_movie"));
                movie.setGenre(resultSet.getString("genre"));
                movie.setRelease_year(resultSet.getInt("release_year"));
                movie.setDuration(resultSet.getString("duration"));
                movie.setDescription(resultSet.getString("description"));

                moviesList.add(movie);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Truyền dữ liệu sang JSP
        request.setAttribute("moviesList", moviesList);
        request.getRequestDispatcher("movie-details.jsp").forward(request, response);
    }
}
