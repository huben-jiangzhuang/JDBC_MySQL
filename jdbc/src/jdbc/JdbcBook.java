package jdbc;

import book.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcBook {
    public static void main(String[] args) {
        List<Book> all = new JdbcBook().findAll();
        System.out.println(all);
        System.out.println(all.size());
        for (Book book : all) {
            System.out.println(book);
        }
    }
    //查询所有book对象,把数据库查询返回结果存储在对象中
     public List<Book> findAll(){
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        Book book = new Book();
        List<Book> books = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.132.2:3306/mysql_test", "mysql_test", "BJjbHSKFGESJrtpP");
            statement = connection.createStatement();
            String sql = "select * from book";
            resultSet = statement.executeQuery(sql);
            books = new ArrayList<>();
            while(resultSet.next()){
                String name = resultSet.getString(1);
                String author = resultSet.getString(2);
                book.setName(name);
                book.setAuthor(author);
                books.add(book);
                return books;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(statement != null){
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(connection != null){
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return books;
        }

    }
}
