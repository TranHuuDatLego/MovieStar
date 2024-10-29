package com.example.moviestar;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductManagement implements Repository<Movies, Integer> {
    private String serverUrl;
    private String user;
    private String password;
    private  String dbName;

    public ProductManagement(String serverUrl, String user, String password) {
        this.serverUrl = serverUrl;
        this.user = user;
        this.password = password;
        init();
    }
    public void init() {
        try {
            Connection conn = DriverManager.getConnection(this.serverUrl, this.user, this.password);
            createDatabaseIfNotExist(conn);
            createMoviesTableIfNotExist(conn);
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public Connection getConnection() {
        try {
            Connection conn = DriverManager.getConnection(this.serverUrl + "/" + this.dbName, this.user, this.password);
            return conn;
        } catch (SQLException e) {
            return null;
        }
    }

    /**
     * create a database
     */
    public void createDatabaseIfNotExist(Connection conn) {

        try {       String sql1 = "CREATE DATABASE IF NOT EXISTS " + this.dbName;

            PreparedStatement ptm1 = conn.prepareStatement(sql1);
            String sql2 = "use " + this.dbName;
            PreparedStatement ptm2 = conn.prepareStatement(sql2);

            ptm1.executeUpdate();
            ptm2.execute();

            ptm1.close();
            ptm2.close();
        } catch (SQLException e) {
            System.out.println("Oops, did not create database @@ !");
            e.printStackTrace();
        }
    }

    /**
        create a table
     */
    public void createMoviesTableIfNotExist(Connection conn) {
        try {
            String sql1 = "DROP TABLE IF EXISTS Movies";
            PreparedStatement ptm1 = conn.prepareStatement(sql1);

            String sql2 = "\n" +
                    "CREATE TABLE Movies\n" +
                    "(\n" +
                    "  id_movie INT NOT NULL,\n" +
                    "  name_movie INT NOT NULL,\n" +
                    "  genre INT NOT NULL,\n" +
                    "  PRIMARY KEY (id_movie)\n" +
                    ");";

            PreparedStatement ptm2 = conn.prepareStatement(sql2);

            ptm1.executeUpdate();
            ptm2.executeUpdate();

            ptm1.close();
            ptm2.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Movies> readAll() {
        List<Movies> results = new ArrayList<>();

        String sql = "SELECT * FROM Movies";
        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            conn = getConnection();
            statement = conn.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Movies movies = new Movies();

                movies.setId_movies(resultSet.getInt("id_movie"));
                movies.setName_movies(resultSet.getString("name_movie"));
                movies.setGenre(resultSet.getString("genre"));
                results.add(movies);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return results;
    }
    @Override
    public Integer add(Movies item) {

        Connection conn = null;
        PreparedStatement statement = null;  // Đổi từ Statement sang PreparedStatement
        ResultSet resultSet = null;          // Khai báo biến resultSet
        Integer id = null;                   // Khai báo biến id
        String sql = "INSERT INTO Movies (name_movie, genre) VALUES (?, ?)";
        
        try {
            conn = getConnection();          // Giả định bạn đã có hàm getConnection()
            conn.setAutoCommit(false);       // Đặt autocommit là false để quản lý giao dịch thủ công
    
            // Chuẩn bị PreparedStatement thay vì Statement
            statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            
            // Đặt giá trị cho các tham số của câu lệnh SQL
            statement.setNString(1, item.getName_movies());   // Giả định item.getName() trả về String
            statement.setNString(2, item.getGenre());      // Giả định item.getGenre() trả về số nguyên
            
            // Thực thi lệnh SQL
            statement.executeUpdate();
            
            // Lấy khóa tự sinh (nếu có)
            resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getInt(1);  // Lấy giá trị của cột khóa tự sinh đầu tiên
            }
    
            // Commit transaction
            conn.commit();
    
            return id;
        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback();  // Rollback nếu xảy ra lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            // Đảm bảo các tài nguyên được đóng đúng cách
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    
        return null;  // Trả về null nếu có lỗi xảy ra
    }
    

    @Override
    public Movies read(Integer id) {
        return null;
    }

    @Override
    public boolean update(Movies item) {
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
